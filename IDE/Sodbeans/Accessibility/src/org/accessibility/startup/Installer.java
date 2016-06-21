/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.startup;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import javax.accessibility.AccessibleContext;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.accessibility.ScreenReader;
import org.accessibility.options.AccessibilityOptions;
import org.accessibility.reading.ScreenReaderImpl;
import org.accessibility.windows.AccessibilityStartup;
import org.netbeans.spi.editor.hints.Fix;
import org.openide.modules.InstalledFileLocator;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.sodbeans.phonemic.tts.TextToSpeechEngine;

public class Installer extends ModuleInstall implements Runnable{
    
    public static final String MAC_SPEECH = "modules/ext/CocoaSpeechServer.app/Contents/MacOS/CocoaSpeechServer";
    public static final String CODE_NAME_BASE = "org.accessibility";
    public static final String STARTUP_STRING = "Starting NetBeans";
    private ScreenReader reader = null;
    private TextToSpeech speech = null;
    
    /**
     * This action basically checks to see if there is a sleep script installed
     * for either JAWS (versions 9 - 16) or NVDA. If there is one installed, 
     * then the user must have installed Sodbeans from a non-plugin, which means 
     * they may be using a screen reader. If the user installed from a plugin, 
     * there will be no sleep script and we will return false.
     * 
     * TODO: Check the App Data folder properly. Currently, there are stubs 
     * in place until those locations are determined.
     * 
     * @return 
     */
    public static boolean isJawsSleepScriptDetected() {
        String env = System.getenv("APPDATA");
        if(env == null) { //either the folder cannot be found or we are not on Windows
            return false;
        }
        //support JAWS versions 9 through 16
        for(int i = 9; i < 17; i++) {
            File file = new File(env + "\\Freedom Scientific\\JAWS\\14h.0\\Settings\\enu\\sodbeans.jcf");
            if(file.exists()) {
                //the file exists on the system, so there is a sleep script installed 
                //for Sodbeans. Return true so that the system knows to temporarily 
                //turn on self-voicing
                return true;
            }
        }
        
        //check if the NVDA sleep scripts were installed.
        return false;
    }
    
    public static boolean isNVDASleepScriptDetected() {
        String env = System.getenv("APPDATA");
        if(env == null) { //either the folder cannot be found or we are not on Windows
            return false;
        }
        
        File file = new File(env + "\\nvda\\appModules\\sodbeans.py");
        if(file.exists()) { //we have an NVDA script, so same deal
            return true;
        }
        
        //check if the NVDA sleep scripts were installed.
        return false;
    }
    
    public static boolean isJAWSRunning() {
        return isProcessRunning("jfw");
    }
    
    public static boolean isNVDARunning() {
        return isProcessRunning("nvda");
    }
    
    public static boolean isProcessRunning(String process) {
        try {
            String line;
            String pidInfo ="";
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            if(p == null) {
                return false;
            }
            BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) { 
                pidInfo+=line;
            }
            input.close();
            if(pidInfo.contains(process))
            {
                return true;
            }
        } catch (IOException ex) {
        }
        return false;
    }
    
    public boolean isWindowsScreenReaderAvailable() {
        Iterator<TextToSpeechEngine> engines = speech.getAvailableEngines();
        while(engines.hasNext()) {
            TextToSpeechEngine engine = engines.next();
            if(engine == TextToSpeechEngine.JAWS ||
               engine == TextToSpeechEngine.NVDA) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void restored() {
        File file = InstalledFileLocator.getDefault().locate(
                MAC_SPEECH, CODE_NAME_BASE, false);
        //this is a workaround for NetBeans blowing away execute permissions
        //when it builds into the cluster.
        file.setExecutable(true);
        
        WindowManager manager = WindowManager.getDefault();
        manager.invokeWhenUIReady(this);
    }

    public boolean detectSleepingScreenReader() {
        if(isJAWSRunning() && isJawsSleepScriptDetected()) {
            return true;
        }
        
        if(isNVDARunning() && isNVDASleepScriptDetected()) {
            return true;
        }
        
        return false;
    }
    @Override
    public void run() {
        speech = TextToSpeechFactory.getDefaultTextToSpeech();
        JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
        if(!AccessibilityOptions.isStartedOnce()) {
            //if we are on windows and a windows screen reader is available, 
            //then we need to check if the user installed a sleep script 
            boolean sleeping = detectSleepingScreenReader();
            if(sleeping) {
                AccessibilityOptions.setSelfVoicing(true);
            }
            AccessibilityStartup startup = new AccessibilityStartup(frame, true);
            startup.setLocationRelativeTo(frame);
            startup.setVisible(true);
            boolean voiced = startup.isSelfVoiced();            
            AccessibilityOptions.setDefaultAccessibilityOptions(voiced);
            AccessibilityOptions.setStartedOnce(true);
        }
        
        AccessibilityOptions.setSystemOptions();
        if(AccessibilityOptions.isSelfVoicing()) {
            speech.speak(STARTUP_STRING);
        }
        
        reader = new ScreenReaderImpl();
        reader.gainControl();
        setMagnifierSupport();
        SetWindowSupport();
        SetEditorHintSupport();
    }
    
    private void setMagnifierSupport() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new GlobalListener(), AWTEvent.KEY_EVENT_MASK);

            // For the magnifier
            Toolkit.getDefaultToolkit().addAWTEventListener(new MagnifierListener(), AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.WINDOW_EVENT_MASK);
            Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

                public void eventDispatched(AWTEvent e) {

                    if (e instanceof WindowEvent) {
                        WindowEvent w = (WindowEvent) e;
                        String param = w.paramString();
                        if (w.getID() == WindowEvent.WINDOW_OPENED) {
                            
                            Window window  = w.getWindow();
                            // Is it a code completion window?
                            boolean isCodeCompletion = isCodeCompletionWindow(window, true, !CodeCompletionListener.isCodeCompletionOpen());

                            if (!isCodeCompletion) {
                                AccessibleContext ac = window.getAccessibleContext();
                                if (ac != null) {
                                    String name = ac.getAccessibleName();
                                    if (name == null) {
                                        name = "";
                                    }
                                    if (AccessibilityOptions.isSelfVoicing() && !name.trim().isEmpty()) {
                                        speech.speak(name + " window opened", SpeechPriority.MEDIUM_HIGH);
                                    }
                                }
                            }
                        } else if (w.getID() == WindowEvent.WINDOW_CLOSING) {
                            Window window = w.getWindow();
                            AccessibleContext ac = window.getAccessibleContext();
                            // Is it a code completion window?
                            if (ac != null) {
                                String name = ac.getAccessibleName();
                                if (name == null) {
                                    name = "";
                                }
                                if (AccessibilityOptions.isSelfVoicing()) {
                                    speech.speak(name + " window closing", SpeechPriority.MEDIUM_HIGH);
                                }
                            }
                        }
                    }
                }
            }, AWTEvent.WINDOW_EVENT_MASK);
    }
    
    private void SetWindowSupport() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent e) {
                if (e instanceof WindowEvent) {
                    final int MINIMIZED = 7;
                    final int MINIMIZED2 = 1;
                    WindowEvent w = (WindowEvent) e;
                    //I would think that there are constants in java.awt.Frame,
                    //but I did not immediatley see them for minimized windows.
                    if (w.getNewState() == MINIMIZED || w.getNewState() == MINIMIZED2) {
                        Window window = w.getWindow();
                        AccessibleContext ac = window.getAccessibleContext();
                        if (ac != null) {
                            String name = ac.getAccessibleName();
                            if (name == null) {
                                name = "";
                            }
                            if (AccessibilityOptions.isSelfVoicing()) {
                                speech.speak(name + " window minimized", SpeechPriority.MEDIUM_HIGH);
                            }
                        }
                    }
                }
            }
        }, AWTEvent.WINDOW_STATE_EVENT_MASK);
    }
    
    private void SetEditorHintSupport() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

            public void eventDispatched(AWTEvent evt) {
                if (AccessibilityOptions.isSelfVoicing()) {
                    HierarchyEvent e = (HierarchyEvent) evt;
                    if (e.getChangeFlags() == HierarchyEvent.DISPLAYABILITY_CHANGED) {
                        Object source = e.getSource();

                        // Is this the code completion window?
                        if (source instanceof JList && source.getClass().getName().equals("org.netbeans.modules.editor.completion.CompletionJList")) {
                            JList jl = (JList)source;
                            if (jl.isDisplayable()) {
                                //if (!CodeCompletionListener.isCodeCompletionOpen()) {
                                    JList list = (JList) source;
                                    list.addListSelectionListener(CodeCompletionListener.getInstance());
                                    SwingUtilities.getWindowAncestor(jl).addWindowListener(CodeCompletionListener.getInstance());

                                    Object selectedValue = list.getSelectedValue();
                                    CodeCompletionListener.getInstance().read(selectedValue);
                                //}
                            }
                        }
                        // Is it the combo box popup?
                        if (source instanceof JList && source.getClass().getName().equals("org.netbeans.modules.editor.hints.borrowed.ListCompletionView")) {
                            JList list = (JList) source;

                            if (list.isDisplayable()) { // about to become visible
                                list.addListSelectionListener(EditorHintsListener.getInstance());
                                list.addComponentListener(EditorHintsListener.getInstance());

                                // Attempt to speak selection.
                                Object selectedValue = list.getSelectedValue();
                                if (AccessibilityOptions.isSelfVoicing() && selectedValue != null && selectedValue instanceof Fix) {
                                    Fix f = (Fix) selectedValue;
                                    speech.speak(f.getText(), SpeechPriority.MEDIUM_HIGH);
                                }
                            } else {
                                if (AccessibilityOptions.isSelfVoicing()) {
                                    speech.speak("Editor hints closing", SpeechPriority.MEDIUM_HIGH);
                                }
                            }
                        } // ... or the tooltip? Editor Tooltips, (as determined from the Netbeans Source),
                        // are instances of JTextAreas.
                        else if (source.getClass().getName().startsWith("org.netbeans.editor.ext.ToolTipSupport")) {
                            if (source instanceof JTextArea) {
                                JTextArea t = (JTextArea) source;
                                if (AccessibilityOptions.isSelfVoicing() && t.isDisplayable()) // it is about to be displayed
                                {
                                    speech.speak("Tooltip: " + t.getText(), SpeechPriority.MEDIUM);
                                }
                            }
                        }
                    }
                }
            }
        }, AWTEvent.HIERARCHY_EVENT_MASK);
    }
    
    /**
     * Determine if the given window instance is a code-completion pop-up.
     * Optionally, the appropriate event listeners are also added.
     *
     * @param window
     * @param connectListeners should the key and list listeners be hooked?
     * @param speak should the current selection be spoken?
     * @return
     */
    private boolean isCodeCompletionWindow(Window window, boolean connectListeners, boolean speak) {
        try {
            Component component = window.getComponent(0);
            if (component instanceof JRootPane) {
                JRootPane jrp = (JRootPane) component;
                    Component firstComponent = jrp.getContentPane().getComponent(0);
                if (firstComponent != null && firstComponent instanceof Container) {
                    Container c = (Container) firstComponent;
                    return findCompletionJList(window, c, connectListeners, speak);
                }
            }
        } catch (Exception e) {
        }

        return false;
    }
    
    private boolean findCompletionJList(Window window, Container c, boolean connectListeners, boolean speak) {
                Component[] components = c.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component o = components[i];
            if (o.getClass().getName().equals("org.netbeans.modules.editor.completion.CompletionJList")) {
                if (connectListeners) {
                    JList list = (JList) o;
                    list.addListSelectionListener(CodeCompletionListener.getInstance());
                    window.addWindowListener(CodeCompletionListener.getInstance());

                    if (AccessibilityOptions.isSelfVoicing()) {
                        Object selectedValue = list.getSelectedValue();
                        CodeCompletionListener.getInstance().read(selectedValue);
                    }
                }
                return true;
            } else if (o instanceof Container) {
                return findCompletionJList(window, (Container) o, connectListeners, speak);
            }
        }

        return false;
    }
}
