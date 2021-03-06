package com.my3o.common.service.task;

/**
 * @author Duckardt
 * 
 */
public abstract class AbstractBaseDaemonBackgroundTask extends AbstractBaseRunnableBackgroundTask {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.common.service.task.IErpRunnableBackgroundTask#isDaemon
     * ()
     */
    @Override
    public boolean isDaemon() {
        return true;
    }

}
