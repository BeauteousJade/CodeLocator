package com.bytedance.tools.codelocator.utils;

import com.bytedance.tools.codelocator.dialog.ShowConfigDialog;
import com.bytedance.tools.codelocator.model.CodeLocatorUserConfig;
import com.bytedance.tools.codelocator.model.UpdateInfo;
import com.bytedance.tools.codelocator.panels.CodeLocatorWindow;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;

public class AutoUpdateUtils {

    private static long sLastCheckUpdateTime = 0;

    private static String sCurrentPluginVersion;

    private static String sMinSupportSdkVersion;

    private static String sChangeLog;

    private static String sChangeNews = "";

    public static File sUpdateFile = null;

    public static UpdateInfo sUpdateInfo = null;

    public static boolean sHasShowUpdateNotice = false;

    public static String getMinSupportSdkVersion() {
        return sMinSupportSdkVersion;
    }

    public static String getChangeLog() {
        return sChangeLog;
    }

    public static String getCurrentPluginVersion() {
        if (sCurrentPluginVersion != null) {
            return sCurrentPluginVersion;
        }
        String version = "9.99.999";
        try {
            InputStream resourceAsStream = null;
            if (ResUtils.currentRes.contains("en")) {
                resourceAsStream = AutoUpdateUtils.class.getResourceAsStream("/META-INF/version_en");
            } else {
                resourceAsStream = AutoUpdateUtils.class.getResourceAsStream("/META-INF/version");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, FileUtils.CHARSET_NAME));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("<version>")) {
                    final String subVersion = line.substring("<version>".length());
                    int indexOfLeft = subVersion.indexOf("<");
                    if (indexOfLeft < 0) {
                        continue;
                    }
                    sCurrentPluginVersion = subVersion.substring(0, indexOfLeft);
                    version = sCurrentPluginVersion;
                } else if (line.startsWith("<min-support-sdk>")) {
                    final String subVersion = line.substring("<min-support-sdk>".length());
                    int indexOfLeft = subVersion.indexOf("<");
                    if (indexOfLeft < 0) {
                        continue;
                    }
                    sMinSupportSdkVersion = subVersion.substring(0, indexOfLeft);
                } else if (line.startsWith("<changelog>")) {
                    final String subVersion = line.substring("<changelog>".length());
                    int indexOfLeft = subVersion.indexOf("<");
                    if (indexOfLeft < 0) {
                        continue;
                    }
                    sChangeLog = subVersion.substring(0, indexOfLeft);
                }
            }
            bufferedReader.close();

            if (ResUtils.currentRes.contains("en")) {
                resourceAsStream = AutoUpdateUtils.class.getResourceAsStream("/META-INF/versionNews_en");
            } else {
                resourceAsStream = AutoUpdateUtils.class.getResourceAsStream("/META-INF/versionNews");
            }
            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, FileUtils.CHARSET_NAME));
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
            sChangeNews = sb.toString();
            bufferedReader.close();
        } catch (Exception e) {
            Log.e("readVersion Error", e);
        }

        return version;
    }

    public static String getChangeNews() {
        if (sChangeNews != null || !sChangeNews.isEmpty()) {
            return sChangeNews;
        }
        return getChangeLog();
    }

    public static void checkNeedUpdate(CodeLocatorWindow codeLocatorWindow) {
        if (Math.abs(System.currentTimeMillis() - sLastCheckUpdateTime) < 6 * 60 * 60 * 1000L) {
            return;
        }
        checkForUpdate(codeLocatorWindow);
    }

    public static void checkForUpdate(CodeLocatorWindow codeLocatorWindow) {
        NetUtils.fetchConfig();
        sLastCheckUpdateTime = System.currentTimeMillis();
        NetUtils.checkForUpdate(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("检测升级失败, 当前版本号: " + getCurrentPluginVersion(), e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                response.body().close();
                final UpdateInfo updateInfo = GsonUtils.sGson.fromJson(responseText, UpdateInfo.class);
                if (updateInfo == null
                    || updateInfo.code != 0) {
                    Log.e("检测升级异常, 当前版本号: " + getCurrentPluginVersion() + " " + responseText);
                    return;
                }

                CodeLocatorUserConfig.updateConfig(CodeLocatorUserConfig.loadConfig());

                if (updateInfo.openUrlMsg != null
                    && updateInfo.openUrlVersion != null
                    && !updateInfo.restart) {
                    ShowConfigDialog.checkAndShowDialog(codeLocatorWindow.getProject(),
                        updateInfo.openUrlVersion,
                        updateInfo.openUrlMsg,
                        updateInfo.openUrlBtn,
                        updateInfo.openUrl,
                        updateInfo.cancelBtn,
                        false,
                        updateInfo.funnyNo);
                }

                if (updateInfo.version == null
                    || updateInfo.version.isEmpty()
                    || updateInfo.url == null
                    || updateInfo.url.isEmpty()) {
                    Log.d("当前版本: " + getCurrentPluginVersion() + " 无需升级");
                    return;
                }

                if (sUpdateInfo != null && updateInfo.version.equals(sUpdateInfo.version)) {
                    Log.d("版本号与上次一致 无需升级 ");
                    sUpdateInfo = updateInfo;
                    updatePluginByUrl(sUpdateInfo.url, sUpdateInfo.md5, codeLocatorWindow);
                    return;
                }

                sUpdateInfo = updateInfo;

                updatePluginByUrl(sUpdateInfo.url, sUpdateInfo.md5, codeLocatorWindow);

                Log.d("检测升级成功, 当前版本号: " + getCurrentPluginVersion()
                    + ", 强制升级: " + sUpdateInfo.needUpdate
                    + ", 升级版本: " + sUpdateInfo.version
                    + ", 下载地址: " + sUpdateInfo.url);
            }
        });
    }

    public static void updatePluginByUrl(String url, String md5, CodeLocatorWindow codeLocatorWindow) {
        if (url == null || url.isEmpty()) {
            return;
        }
        final File updatePluginFile = new File(FileUtils.sCodeLocatorMainDirPath, FileUtils.UPDATE_FILE_NAME);
        if (updatePluginFile.exists()) {
            final String currentMD5 = MD5Utils.getMD5(updatePluginFile);
            if (currentMD5 != null && currentMD5.equals(md5)) {
                sUpdateFile = updatePluginFile;
                notifyCanUpdate(codeLocatorWindow, updatePluginFile);
                Log.d("本地已经存在升级文件 无需下载 " + url);
                return;
            } else {
                updatePluginFile.delete();
            }
        }
        downloadUrlToFile(url, FileUtils.sCodeLocatorMainDirPath, FileUtils.UPDATE_TMP_FILE_NAME, new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file, long total) {
                Log.d("下载 " + url + " 成功, 文件路径: " + updatePluginFile.getAbsolutePath() + ", 下载文件大小 " + file.length() + ", total: " + total);
                if (file.exists()) {
                    final boolean renameSuccess = file.renameTo(updatePluginFile);
                    if (renameSuccess && updatePluginFile.exists()) {
                        sUpdateFile = updatePluginFile;
                        Mob.mob(Mob.Action.EXEC, "download_" + sUpdateInfo.version);
                        notifyCanUpdate(codeLocatorWindow, updatePluginFile);
                    }
                }
            }

            @Override
            public void onDownloadFailed(Exception e) {
                Log.e("下载 " + url + " 失败", e);
            }
        });
    }

    private static void notifyCanUpdate(CodeLocatorWindow codeLocatorWindow, File updatePluginFile) {
        ThreadUtils.runOnUIThread(() -> {
            codeLocatorWindow.showCanUpdate(sUpdateInfo.version, sUpdateInfo.needUpdate, sUpdateInfo.iconPos, updatePluginFile);
            if (sUpdateInfo.restart && !sUpdateInfo.needUpdate) {
                ShowConfigDialog.checkAndShowDialog(codeLocatorWindow.getProject(),
                    sUpdateInfo.openUrlVersion,
                    sUpdateInfo.openUrlMsg,
                    sUpdateInfo.openUrlBtn,
                    sUpdateInfo.openUrl,
                    sUpdateInfo.cancelBtn,
                    sUpdateInfo.restart,
                    sUpdateInfo.funnyNo);
            } else if (!sUpdateInfo.needUpdate && !sHasShowUpdateNotice) {
                sHasShowUpdateNotice = true;
                String newVersionTip = ResUtils.getString("update_tips");
                if (sUpdateInfo.news != null && !sUpdateInfo.news.isEmpty()) {
                    newVersionTip = (sUpdateInfo.news).replace("\n", "<br>");
                }
                NotificationUtils.showNotifyInfoShort(codeLocatorWindow.getProject(), newVersionTip, 30000L);
            }
        });
    }

    public static boolean updatePlugin() {
        if (sUpdateFile == null || !sUpdateFile.exists()) {
            Log.d("升级文件为空");
            return false;
        }
        try {
            FileUtils.deleteFile(new File(FileUtils.sPluginInstallDir, "CodeLocatorPlugin"));
            ZipUtils.unZip(sUpdateFile.getAbsoluteFile(), FileUtils.sPluginInstallDir);
            sUpdateFile.delete();
            Log.d("CodeLocator 已强制升级到 " + sUpdateInfo.version);
            Mob.mob(Mob.Action.EXEC, Mob.Button.FORCE_UPDATE + " " + sUpdateInfo.version);
            return true;
        } catch (Exception e) {
            Log.e("解压升级失败", e);
        }
        return false;
    }

    public static void downloadUrlToFile(final String url, String destFileDir, final String destFileName, final OnDownloadListener listener) {
        if (url == null || url.isEmpty()) {
            return;
        }
        Request request = new Request.Builder().url(url).build();
        //异步请求
        NetUtils.sOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream is = null;
                byte[] buf = new byte[4096];
                int len = 0;
                FileOutputStream fos = null;
                File file = new File(destFileDir, destFileName);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    listener.onDownloadSuccess(file, total);
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    public static long getVersionNum(String versionStr) {
        if (versionStr == null || versionStr.isEmpty()) {
            return 0L;
        }
        try {
            long version = 0;
            final String[] split = versionStr.split("\\.");
            for (int i = split.length - 1; i > -1; i--) {
                version = (version + ((long) Math.pow(1000, (split.length - i - 1))) * Integer.valueOf(split[i]));
            }
            return version;
        } catch (Exception e) {
            Log.e("getversion Error " + versionStr, e);
        }
        return 0L;
    }

    public interface OnDownloadListener {
        /**
         * 下载成功之后的文件
         */
        void onDownloadSuccess(File file, long total);

        /**
         * 下载异常信息
         */
        void onDownloadFailed(Exception e);
    }
}
