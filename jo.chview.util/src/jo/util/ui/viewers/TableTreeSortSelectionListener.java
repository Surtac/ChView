/*
 * Created on Jun 27, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package jo.util.ui.viewers;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TreeColumn;

public class TableTreeSortSelectionListener implements SelectionListener {
    private final TreeViewer viewer;
    private final TreeColumn column;
    private final InvertableSorter sorter;
    private final boolean keepDirection;
    private InvertableSorter currentSorter;
 
    /**
     * The constructor of this listener.
     * 
     * @param viewer
     *            the tableviewer this listener belongs to
     * @param column
     *            the column this listener is responsible for
     * @param sorter
     *            the sorter this listener uses
     * @param defaultDirection
     *            the default sorting direction of this Listener. Possible
     *            values are {@link SWT.UP} and {@link SWT.DOWN}
     * @param keepDirection
     *            if true, the listener will remember the last sorting direction
     *            of the associated column and restore it when the column is
     *            reselected. If false, the listener will use the default soting
     *            direction
     */
    public TableTreeSortSelectionListener(TreeViewer viewer, TreeColumn column,
            AbstractInvertableTableSorter sorter, int defaultDirection,
            boolean keepDirection) {
        this.viewer = viewer;
        this.column = column;
        this.keepDirection = keepDirection;
        this.sorter = (defaultDirection == SWT.UP) ?
            sorter : sorter.getInverseSorter();
        this.currentSorter = this.sorter;
 
        this.column.addSelectionListener(this);
    }
 
    /**
     * Chooses the colum of this listener for sorting of the table. Mainly used
     * when first initialising the table.
     */
    public void chooseColumnForSorting() {
        viewer.getTree().setSortColumn(column);
        viewer.getTree().setSortDirection(currentSorter.getSortDirection());
        viewer.setSorter(currentSorter);
    }
 
    public void widgetSelected(SelectionEvent e) {
        InvertableSorter newSorter;
        if (viewer.getTree().getSortColumn() == column) {
            newSorter = ((InvertableSorter) viewer.getSorter())
                    .getInverseSorter();
        } else {
            if (keepDirection) {
                newSorter = currentSorter;
            } else {
                newSorter = sorter;
            }
        }
 
        currentSorter = newSorter;
        chooseColumnForSorting();
    }
 
    public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
    }
}
