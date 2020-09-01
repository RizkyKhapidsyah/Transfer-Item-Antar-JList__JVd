package com.rk.tiaj;

/* Created by Rizky Khapidsyah */

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class PelakuTransfer_PadaJList extends TransferHandler {

    DataFlavor dataFlavor;
    int[] selectedIndexes = null;

    public PelakuTransfer_PadaJList() {
        dataFlavor = new ActivationDataFlavor(Object[].class, DataFlavor.javaJVMLocalObjectMimeType);
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JList sourceList = (JList) c;
        selectedIndexes = sourceList.getSelectedIndices();
        Object[] transferedObjects = sourceList.getSelectedValues();

        return new DataHandler(transferedObjects, dataFlavor.getMimeType());
    }

    @Override
    public boolean canImport(TransferSupport ts) {
        if (ts.isDrop() && ts.isDataFlavorSupported(dataFlavor)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    public boolean importData(TransferSupport ts) {
        boolean result = false;

        if (canImport(ts)) {
            JList destinationList = (JList) ts.getComponent();
            DefaultListModel model = (DefaultListModel) destinationList.getModel();
            JList.DropLocation dropLocation = (JList.DropLocation) ts.getDropLocation();
            int index = dropLocation.getIndex();

            try {
                Object[] data = (Object[]) ts.getTransferable().getTransferData(dataFlavor);

                for (int i = 0; i < data.length; i++) {
                    model.add(index, data[i]);
                    index++;
                }

                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        JList sourceList = (JList) c;
        DefaultListModel model = (DefaultListModel) sourceList.getModel();

        for (int i = selectedIndexes.length - 1; i >= 0; i--) {
            model.remove(selectedIndexes[i]);
        }
    }

}
