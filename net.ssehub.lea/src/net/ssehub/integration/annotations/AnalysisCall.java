package net.ssehub.integration.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ssehub.integration.Call;
import net.ssehub.integration.ElementType;
import net.ssehub.integration.ExternalElementException;

/**
 * Indicates that the annotated method represents a {@link Call} of the type {@link ElementType#ANALYSIS_CALL}.
 * 
 * @author Christian Kroeher
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface AnalysisCall {

    /**
     * Returns the (symbolic) name of this {@link ElementType#ANALYSIS_CALL}. If this name is defined, it is proposed
     * as language element instead of using the name of the annotated method.
     * 
     * @return the (symbolic) name of this {@link ElementType#ANALYSIS_CALL}; the default value is an <i>empty</i>
     *         string
     */
    public String name() default "";
    
    /**
     * Returns the (symbolic) name of the return type of this {@link ElementType#ANALYSIS_CALL}. If this return type
     * name is defined, it is used as part of the proposal of the actual call the annotated method represents
     * instead of using the name of the actual return type of that method.<br>
     * <br>
     * <b>Important:</b> If the annotated method has no return type (<code>void</code>), this parameter must be defined
     * as an analysis call is solely used for providing results and, hence, requires a return type; otherwise the
     * {@link Call} constructor will throw an {@link ExternalElementException} and the analysis call will not be
     * available as part of the language. 
     * 
     * @return the (symbolic) name of the return type of this {@link ElementType#ANALYSIS_CALL}; the default value is an
     *         <i>empty</i> string
     */
    public String returnType() default "";
    
    /**
     * Returns an array of (symbolic) names representing the parameters of this {@link ElementType#ANALYSIS_CALL}. If
     * this array is defined, its elements are used as part of the proposal of the actual call the annotated method
     * represents instead of using the names of the actual parameters of that method.
     * 
     * @return the array of (symbolic) names representing the parameters of this {@link ElementType#ANALYSIS_CALL}; the
     *         default value is an <i>empty</i> array
     */
    public String[] parameters() default { };
}
