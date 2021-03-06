package com.my3o.common.service.task;


/**
 * @author Alexander Dukkardt
 * 
 */
public interface IBaseRunnableBackgroundTask extends IBaseBackgroundTask,
        Runnable {

    public IBaseRunnableBackgroundTask cloneTask() throws Exception;

    public boolean isDaemon();

}
