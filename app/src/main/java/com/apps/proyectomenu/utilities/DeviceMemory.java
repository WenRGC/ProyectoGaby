package com.apps.proyectomenu.utilities;

import android.os.Environment;
import android.os.StatFs;

public class DeviceMemory {

    public static long getInternalUsedSpace()
    {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        //StatFs statFs = new StatFs("/data");
        long total = ((long)statFs.getBlockCount() * (long)statFs.getBlockSize()) / 1048576;
        long free  = ((long)statFs.getAvailableBlocks() * (long)statFs.getBlockSize()) / 1048576;
        long busy  = total - free;
        return busy;
    }
}