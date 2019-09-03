package net.ssehub.integration;

/**
 * This interface declares a single method to be implemented by each specific {@link LanguageElement} that allows the
 * construction of premature objects and their later completion. Hence, the method declared by this interface enables a
 * check, if the object construction is already completed. 
 * 
 * @author Christian Kroeher
 *
 */
public interface IFinalizable {

    /**
     * Checks whether the construction of this element is completed.
     * 
     * @return <code>true</code>, if the construction of this element is completed; <code>false</code> otherwise
     */
    public boolean isFinal();
}
