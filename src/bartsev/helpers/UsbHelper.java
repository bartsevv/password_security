package bartsev.helpers;

import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;

import java.util.ArrayList;
import java.util.List;

public class UsbHelper {

    public static List<String> getListOfUsb() {
        USBDeviceDetectorManager manager = new USBDeviceDetectorManager();
        List<USBStorageDevice> usbStorageDevices = manager.getRemovableDevices();
        List<String> listOfRemovableDevice = new ArrayList<>();
        for (USBStorageDevice usbStorageDevice : usbStorageDevices) {
            listOfRemovableDevice.add(usbStorageDevice.getDeviceName());
        }
        listOfRemovableDevice.add("ANG");
        return listOfRemovableDevice;
    }
}



