package jo.d2k.admin.rcp.viz.chview.handlers.rep;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import jo.d2k.admin.rcp.viz.chview.logic.ChViewVisualizationLogic;
import jo.d2k.data.logic.report.CubicDensityReport;
import jo.util.ui.act.GenericAction;
import jo.util.utils.io.FileUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class HandlerReportCubicDensity extends AbstractHandler
{

    @Override
    public Object execute(ExecutionEvent ev) throws ExecutionException
    {
        String output = GenericAction.getSaveFile(HandlerReportCubicDensity.class, null, "HTML File", "*.html");
        if (output == null)
            return null;
        File outFile = new File(output);
        String html = CubicDensityReport.generateReport(ChViewVisualizationLogic.mPreferences);
        try
        {
            FileUtils.writeFile(html, outFile);
            Desktop.getDesktop().open(outFile);
        }
        catch (IOException e)
        {
            GenericAction.openError("Cubic Density Report", "Error saving report", e);
        }
        return null;
    }

}
