/*
 * Copyright 2019 University of Hildesheim, Software Systems Engineering
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package net.ssehub.ui.properties;

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

/**
 * This class realizes the LEA properties page accessible via the project's properties.
 * 
 * @author Christian Kroeher
 *
 */
public class PropertiesPage extends PropertyPage implements IWorkbenchPropertyPage {
    
    /**
     * The {@link List} containing each path to search for plug-ins as an individual entry.
     */
    private List pluginPathsUiList;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(Composite parent) {
        noDefaultButton(); // Remove "Default"-button as there will be no default (for now)
        return createPluginPathsComposite(parent);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performApply() {
        // If "Apply"-button is selected, delete old paths from the preferences and add the current ones from the list 
        LeaPreferences.setPluginPaths(pluginPathsUiList.getItems());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        /*
         * If "Apply and Close"-button is selected, delete old paths from the preferences and add the current ones from
         * the list
         */
        LeaPreferences.setPluginPaths(pluginPathsUiList.getItems());
        /*
         * Further, only if "Apply and Close"-button is selected, the language registry is cleared and new language
         * elements based on the current paths are added
         */
        try {
            LeaCoreConnector.INSTANCE.provideLanguageElements();
        } catch (LanguageElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
    
    /**
     * Creates and returns the {@link Composite}, which contains the {@link #pluginPathsUiList} as well as an 
     * <i>Add...</i> and <i>Delete</i> button for adding new or deleting existing plug-in paths.
     * 
     * @param parent the parent {@link Composite} of the one created by this method
     * @return the {@link Composite} for displaying as well as adding or deleting paths to search for plug-ins
     */
    private Composite createPluginPathsComposite(Composite parent) {
        Composite pluginPathsComposite = new Composite(parent, SWT.NONE);
        
        // The composite should have two columns: left is the path list and right the add/delete path(s) buttons
        GridLayout compositeGridLayout = new GridLayout(2, false);
        pluginPathsComposite.setLayout(compositeGridLayout);
        /*
         * The composite should always fill the parent horizontally (horizontalAlignment) and vertically
         * (verticalAlignment), even if it will be resized (grabExcess*Space).
         */
        GridData compositeGridData = new GridData();
        compositeGridData.horizontalAlignment = SWT.FILL;
        compositeGridData.grabExcessHorizontalSpace = true; 
        compositeGridData.verticalAlignment = SWT.FILL;
        compositeGridData.grabExcessVerticalSpace = true;
        pluginPathsComposite.setLayoutData(compositeGridData);
        /*
         * In the first column, add the list of defined plug-in paths. That list should behave in the same way as the
         * composite above (always fill the entire parent). By adding the add/delete path(s) buttons below, this list
         * will span the remaining space of the parent.
         * 
         * Note that this list will be accessed, if the delete button is selected, to delete the selected list items
         * (plug-in path(s)) form the global configuration.
         */
        createPluginPathsList(pluginPathsComposite);
        /*
         * In the second column, add an additional composite, which arranges the add/delete path(s) buttons vertically
         * (add on top and delete on the bottom). Currently, there is no need to further format or style that composite.
         */
        createPluginPathsButtonsComposite(pluginPathsComposite);
        
        return pluginPathsComposite;
    }
    
    /**
     * Creates the {@link List} for displaying the user-defined plug-in paths. That instances is available as the
     * {@link #pluginPathsUiList}.
     * 
     * @param parent the parent {@link Composite} of the created {@link List}, which is created in 
     *        {@link #createPluginPathsComposite(Composite)}
     */
    private void createPluginPathsList(Composite parent) {
        pluginPathsUiList = new List(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        GridData pluginPathListGridData = new GridData();
        pluginPathListGridData.horizontalAlignment = SWT.FILL;
        pluginPathListGridData.grabExcessHorizontalSpace = true; 
        pluginPathListGridData.verticalAlignment = SWT.FILL;
        pluginPathListGridData.grabExcessVerticalSpace = true;
        pluginPathsUiList.setLayoutData(pluginPathListGridData);
        // Add the stored plug-in paths from the LEA-specific preferences to the list
        java.util.List<String> pluginPaths = LeaPreferences.getPluginPaths();
        if (pluginPaths != null) {
            for (String pluginPath : pluginPaths) {
                pluginPathsUiList.add(pluginPath);
            }
        }    
    }
    
    /**
     * Creates the {@link Composite}, which contains the <i>Add...</i> and <i>Delete</i> button for adding new or
     * deleting existing plug-in paths.
     * 
     * @param parent the parent {@link Composite} of the one created by this method, which is created in 
     *        {@link #createPluginPathsComposite(Composite)}
     */
    private void createPluginPathsButtonsComposite(Composite parent) {
        Composite pluginPathsButtonsComposite = new Composite(parent, SWT.NONE);
        // The composite should have a single column: top is the add button and bottom the delete button
        GridLayout compositeGridLayout = new GridLayout(1, false);
        pluginPathsButtonsComposite.setLayout(compositeGridLayout);
        // Add the buttons to the composite
        createPluginPathsButtons(pluginPathsButtonsComposite);
    }
    
    /**
     * Creates the <i>Add...</i> and <i>Delete</i> {@link Button} for adding new or deleting existing plug-in paths.
     * 
     * @param parent the parent {@link Composite} of the created {@link Button}s, which is created in 
     *        {@link #createPluginPathsButtonsComposite(Composite)}
     */
    private void createPluginPathsButtons(Composite parent) {
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
    
    /**
     * Performs the actions associated with clicking the <i>Add...</i> button.
     * 
     * @param parent the parent {@link Composite} of the <i>Add...</i> button, which is created in 
     *        {@link #createPluginPathsButtonsComposite(Composite)}; this parameter is required to create a 
     *        {@link DirectoryDialog} and, optional, {@link MessageBox} as part of the actions
     */
    private void performAddButtonSelectedAction(Composite parent) {
        DirectoryDialog directoryDialog = new DirectoryDialog(parent.getShell(), SWT.OPEN);
        String newPath = directoryDialog.open();
        if (newPath != null && !newPath.isEmpty()) {   
            if (isDuplicate(newPath)) {
                MessageBox infoDialog = new MessageBox(parent.getShell(), SWT.ICON_INFORMATION | SWT.OK);
                infoDialog.setText("Duplicated Path");
                infoDialog.setMessage("The selected directory \"" + newPath + "\" already exists in the list of plug-in"
                        + " search paths.");
                infoDialog.open();
            } else {
                pluginPathsUiList.add(newPath);
            }
        }
    }
    
    /**
     * Checks whether the given plug-in path (string) is already defined in the {@link #pluginPathsUiList}.
     * 
     * @param pluginPath the {@link String} denoting the plug-in path to check for existence in the
     *        {@link #pluginPathsUiList}
     * @return <code>true</code>, if the given plug-in path is already defined in the {@link #pluginPathsUiList};
     *         <code>false</code> otherwise
     */
    private boolean isDuplicate(String pluginPath) {
        boolean isDuplicate = false;
        String[] uiItems = pluginPathsUiList.getItems();
        int uiItemsCounter = 0;
        while (!isDuplicate && uiItemsCounter < uiItems.length) {
            if (pluginPath.equals(uiItems[uiItemsCounter])) {
                isDuplicate = true;
            }
            uiItemsCounter++;
        }
        return isDuplicate;
    }
    
    /**
     * Performs the actions associated with clicking the <i>Delete</i> button.
     */
    private void performDeleteButtonSelectedAction() {
        int[] selectedPluginPathsIndices = pluginPathsUiList.getSelectionIndices();
        if (selectedPluginPathsIndices != null && selectedPluginPathsIndices.length > 0) {
            pluginPathsUiList.remove(selectedPluginPathsIndices);
        }
    }
}
