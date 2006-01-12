/*
 * <license>
 * The contents of this file are subject to the Mozilla Public License 
 * Version 1.1 (the "License"); you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License 
 * at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or 
 * implied. See the License for the specific language governing rights 
 * and limitations under the License.
 * 
 * The Original Code is Web Photo Publisher.
 * 
 * The Initial Developer of the Original Code is Mark Roth.  Portions 
 * created by Mark Roth are Copyright (C) 2003 Mark Roth.  
 * All Rights Reserved.
 * 
 * Contributor(s) listed below.
 * </license>
 */

package com.javaimagealbum;

/**
 * Base class from which all output classes extend.  Provides basic
 * support for progress tracking, and reduces the complexity of
 * the PublishManager.
 * <p>
 * Subclasses should periodically poll isStopGeneration so that the
 * abort button works.
 *
 * @author  mroth
 */
public abstract class OutputStage {

    /** A reference to the PublishManager */
    protected PublishManager publishManager;
    
    /** The total percentage this stage is accounting for */
    private int percentTotal;
    
    /** The percent complete when this stage started */
    private int percentBase;
    
    /** Set to true if we are to stop generation immediately. */
    protected boolean stopGeneration = false;
    
    public static final int TIME_COMPLEXITY_VERY_SLOW = 9;
    public static final int TIME_COMPLEXITY_SLOW = 7;
    public static final int TIME_COMPLEXITY_MODERATE = 5;
    public static final int TIME_COMPLEXITY_FAST = 3;
    public static final int TIME_COMPLEXITY_VERY_FAST = 1;
    
    /** Creates new OutputStage */
    public OutputStage( PublishManager publishManager ) {
        this.publishManager = publishManager;
    }
    
    public void setPercentTotal( int percentTotal ) {
        this.percentTotal = percentTotal;
    }
    
    public void setPercentBase( int percentBase ) {
        this.percentBase = percentBase;
    }
    
    /**
     * Performs generation for this stage
     */
    public abstract void generate();

    /**
     * Returns current progress, on a scale from 0 to 100
     */
    public abstract int getProgress();
    
    /**
     * Returns the time complexity of this task.  This makes the progress
     * meter more accurate.
     */
    public abstract int getTimeComplexity();
    
    /**
     * Sets the generation message, above the status bar
     */
    protected void setGenerationMessage( String message ) {
        publishManager.setGenerationMessage( message );
    }
    
    /**
     * Notifies the GUI to update the progress bar.
     */
    protected void updateProgress() {
        publishManager.setGenerationProgress( percentBase + 
            percentTotal * getProgress() / 100 );
    }

    /**
     * Sets the error message and aborts generation.
     */
    protected void error( String errorMessage ) {
        publishManager.stopGeneration( errorMessage );
    }
    
    /**
     * Returns true if we are to stop generation immediately.
     */
    protected boolean isStopGeneration() {
        return publishManager.isStopGeneration();
    }
    
}
