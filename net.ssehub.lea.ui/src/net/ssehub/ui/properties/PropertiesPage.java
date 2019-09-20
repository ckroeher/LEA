package net.ssehub.ui.properties;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

import net.ssehub.integration.LanguageElementException;
import net.ssehub.integration.LanguageElementProvider;

public class PropertiesPage extends PropertyPage implements IWorkbenchPropertyPage {
    
    private List pluginPathsList;
    
    private java.util.List<String> pluginPathsStringList;

    @Override
    protected Control createContents(Composite parent) {
        noDefaultButton(); // Remove "Default"-button as there will be no default (for now)
        return createPluginPathDefinitionsComposite(parent);
    }
    
    @Override
    protected void performApply() {
        // If "Apply"-button is selected, delete old paths from Config and add the current ones in the list 
//        Config.INSTANCE.resetPluginSearchPaths(pluginPathsStringList); TODO
    }
    
    @Override
    public boolean performOk() {
        // If "Apply and Close"-button is selected, delete old paths from Config and add the current ones in the list
//        Config.INSTANCE.resetPluginSearchPaths(pluginPathsStringList); TODO
        LanguageElementProvider lep = new LanguageElementProvider();
        try {
            lep.provideLanguageElements(pluginPathsStringList);
        } catch (LanguageElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
    
    private Composite createPluginPathDefinitionsComposite(Composite parent) {
        Composite pluginPathDefinitionsComposite = new Composite(parent, SWT.NONE);
        // The composite should have two columns: left is the path list and right the add/delete path(s) buttons
        GridLayout compositeGridLayout = new GridLayout(2, false);
        pluginPathDefinitionsComposite.setLayout(compositeGridLayout);
        /*
         * The composite should always fill the parent horizontally (horizontalAlignment) and vertically
         * (verticalAlignment), even if it will be resized (grabExcess*Space).
         */
        GridData compositeGridData = new GridData();
        compositeGridData.horizontalAlignment = SWT.FILL;
        compositeGridData.grabExcessHorizontalSpace = true; 
        compositeGridData.verticalAlignment = SWT.FILL;
        compositeGridData.grabExcessVerticalSpace = true;
        pluginPathDefinitionsComposite.setLayoutData(compositeGridData);
        
        /*
         * In the first column, add the list of defined plugin paths. That list should behave in the same way as the
         * composite above (always fill the entire parent). By adding the add/delete path(s) buttons below, this list
         * will span the remaining space of the parent.
         * 
         * Note that this list will be accessed, if the delete button is selected, to delete the selected list items
         * (plugin path(s)) form the global configuration.
         */
        createPluginPathsList(pluginPathDefinitionsComposite);
        GridData pluginPathListGridData = new GridData();
        pluginPathListGridData.horizontalAlignment = SWT.FILL;
        pluginPathListGridData.grabExcessHorizontalSpace = true; 
        pluginPathListGridData.verticalAlignment = SWT.FILL;
        pluginPathListGridData.grabExcessVerticalSpace = true;
        pluginPathsList.setLayoutData(pluginPathListGridData);
        
        /*
         * In the second column, add an additional composite, which arranges the add/delete path(s) buttons vertically
         * (add on top and delete on the bottom). Currently, the is no need to further format or style that composite.
         */
        createPluginPathDefinitionButtonsComposite(pluginPathDefinitionsComposite);
        
        return pluginPathDefinitionsComposite;
    }
    
    private void createPluginPathsList(Composite parent) {
        pluginPathsList = new List(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        pluginPathsStringList = new ArrayList<String>(); // Config.INSTANCE.getPluginSearchPaths(); TODO
        
        
        updatePluginPathsList();    
    }
    
    private void updatePluginPathsList() {
        if (pluginPathsList != null) {          
            // Remove all "old" plugin paths as shown in the UI and add the elements of the possibly changed string list
            pluginPathsList.removeAll();
            for (String searchPath : pluginPathsStringList) {
                pluginPathsList.add(searchPath);
            }
        }
    }
    
    private void createPluginPathDefinitionButtonsComposite(Composite parent) {
        Composite pluginPathDefinitionButtonsComposite = new Composite(parent, SWT.NONE);
        // The composite should have a single column: top is the add button and bottom the delete button
        GridLayout compositeGridLayout = new GridLayout(1, false);
        pluginPathDefinitionButtonsComposite.setLayout(compositeGridLayout);
        // Add the buttons to the composite
        createPluginPathDefinitionButtons(pluginPathDefinitionButtonsComposite);
    }
    
    private void createPluginPathDefinitionButtons(Composite parent) {
        // Define the add button and its selection action
        Button addPathButton = new Button(parent, SWT.PUSH);
        addPathButton.setText("Add...");
        addPathButton.setToolTipText("Add new path to list");
        addPathButton.addSelectionListener(new SelectionListener() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                performAddButtonSelectedAction(parent);
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                performAddButtonSelectedAction(parent);
            }
        });
        
        // Define the delete button and its selection action
        Button deletePathButton = new Button(parent, SWT.PUSH);
        deletePathButton.setText("Delete");
        deletePathButton.setToolTipText("Delete selected path(s) from list");
        deletePathButton.addSelectionListener(new SelectionListener() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                performDeleteButtonSelectedAction();
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                performDeleteButtonSelectedAction();
            }
        });
    }
    
    private void performAddButtonSelectedAction(Composite parent) {
        DirectoryDialog directoryDialog = new DirectoryDialog(parent.getShell(), SWT.OPEN);
        String newPath = directoryDialog.open();
        if (newPath != null && !newPath.isEmpty()) {
            if (pluginPathsStringList.contains(newPath)) {
                MessageBox infoDialog = new MessageBox(parent.getShell(), SWT.ICON_INFORMATION | SWT.OK);
                infoDialog.setText("Duplicated Path");
                infoDialog.setMessage("The selected directory \"" + newPath + "\" already exists in the list of plug-in"
                        + " search paths.");
                infoDialog.open();
            } else {
                pluginPathsStringList.add(newPath);
            }
        }
        updatePluginPathsList();
    }
    
    private void performDeleteButtonSelectedAction() {
        String[] selectedPluginPaths = pluginPathsList.getSelection();
        if (selectedPluginPaths.length > 0) {           
            int deleteAnyway = SWT.YES;
            if (selectedPluginPaths.length == pluginPathsStringList.size()) {
                // This means that all paths will be deleted, which may cause problems
                MessageBox errorDialog = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
                errorDialog.setText("Empty Path List");
                errorDialog.setMessage("Deleting all plug-in search paths may yield unexpected behavior.\n\n"
                        + "Delete anyway?");
                deleteAnyway = errorDialog.open();
            }
            if (deleteAnyway == SWT.YES) {          
                for (String selectedPluginPath : selectedPluginPaths) {
                    pluginPathsStringList.remove(selectedPluginPath);
                }
                updatePluginPathsList();
            }
        }
    }
}
