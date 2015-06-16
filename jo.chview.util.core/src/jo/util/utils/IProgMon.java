package jo.util.utils;

// an analogous interface to Eclipse's IProgressMonitor

public interface IProgMon
{
    public void beginTask(String name, int totalWork);
    public void done();
    public void internalWorked(double work);
    public boolean isCanceled();
    public void setCanceled(boolean value);
    public void setTaskName(String name);
    public void subTask(String name);
    public void worked(int work);
}
