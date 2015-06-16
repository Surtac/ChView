/*
 * Created on Jun 27, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package jo.util.ui.viewers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

abstract class InvertableSorter extends ViewerSorter 
{
    public abstract int compare(Viewer viewer, Object e1, Object e2);
    abstract InvertableSorter getInverseSorter();
    public abstract int getSortDirection();
}
