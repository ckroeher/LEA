package net.ssehub.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import net.ssehub.services.LeaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalLeaParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Artifact'", "'Fragment'", "'Result'", "'assign'", "'to'", "';'", "','", "'<'", "'>'", "'['", "']'", "':'", "'='", "'.'", "'('", "')'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalLeaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalLeaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalLeaParser.tokenNames; }
    public String getGrammarFileName() { return "InternalLea.g"; }


    	private LeaGrammarAccess grammarAccess;

    	public void setGrammarAccess(LeaGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleAnalysisDefinition"
    // InternalLea.g:53:1: entryRuleAnalysisDefinition : ruleAnalysisDefinition EOF ;
    public final void entryRuleAnalysisDefinition() throws RecognitionException {
        try {
            // InternalLea.g:54:1: ( ruleAnalysisDefinition EOF )
            // InternalLea.g:55:1: ruleAnalysisDefinition EOF
            {
             before(grammarAccess.getAnalysisDefinitionRule()); 
            pushFollow(FOLLOW_1);
            ruleAnalysisDefinition();

            state._fsp--;

             after(grammarAccess.getAnalysisDefinitionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAnalysisDefinition"


    // $ANTLR start "ruleAnalysisDefinition"
    // InternalLea.g:62:1: ruleAnalysisDefinition : ( ( rule__AnalysisDefinition__Group__0 ) ) ;
    public final void ruleAnalysisDefinition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:66:2: ( ( ( rule__AnalysisDefinition__Group__0 ) ) )
            // InternalLea.g:67:2: ( ( rule__AnalysisDefinition__Group__0 ) )
            {
            // InternalLea.g:67:2: ( ( rule__AnalysisDefinition__Group__0 ) )
            // InternalLea.g:68:3: ( rule__AnalysisDefinition__Group__0 )
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getGroup()); 
            // InternalLea.g:69:3: ( rule__AnalysisDefinition__Group__0 )
            // InternalLea.g:69:4: rule__AnalysisDefinition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__AnalysisDefinition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAnalysisDefinitionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAnalysisDefinition"


    // $ANTLR start "entryRuleChangeIdentifierAssignment"
    // InternalLea.g:78:1: entryRuleChangeIdentifierAssignment : ruleChangeIdentifierAssignment EOF ;
    public final void entryRuleChangeIdentifierAssignment() throws RecognitionException {
        try {
            // InternalLea.g:79:1: ( ruleChangeIdentifierAssignment EOF )
            // InternalLea.g:80:1: ruleChangeIdentifierAssignment EOF
            {
             before(grammarAccess.getChangeIdentifierAssignmentRule()); 
            pushFollow(FOLLOW_1);
            ruleChangeIdentifierAssignment();

            state._fsp--;

             after(grammarAccess.getChangeIdentifierAssignmentRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleChangeIdentifierAssignment"


    // $ANTLR start "ruleChangeIdentifierAssignment"
    // InternalLea.g:87:1: ruleChangeIdentifierAssignment : ( ( rule__ChangeIdentifierAssignment__Group__0 ) ) ;
    public final void ruleChangeIdentifierAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:91:2: ( ( ( rule__ChangeIdentifierAssignment__Group__0 ) ) )
            // InternalLea.g:92:2: ( ( rule__ChangeIdentifierAssignment__Group__0 ) )
            {
            // InternalLea.g:92:2: ( ( rule__ChangeIdentifierAssignment__Group__0 ) )
            // InternalLea.g:93:3: ( rule__ChangeIdentifierAssignment__Group__0 )
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getGroup()); 
            // InternalLea.g:94:3: ( rule__ChangeIdentifierAssignment__Group__0 )
            // InternalLea.g:94:4: rule__ChangeIdentifierAssignment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getChangeIdentifierAssignmentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleChangeIdentifierAssignment"


    // $ANTLR start "entryRuleElementDeclaration"
    // InternalLea.g:103:1: entryRuleElementDeclaration : ruleElementDeclaration EOF ;
    public final void entryRuleElementDeclaration() throws RecognitionException {
        try {
            // InternalLea.g:104:1: ( ruleElementDeclaration EOF )
            // InternalLea.g:105:1: ruleElementDeclaration EOF
            {
             before(grammarAccess.getElementDeclarationRule()); 
            pushFollow(FOLLOW_1);
            ruleElementDeclaration();

            state._fsp--;

             after(grammarAccess.getElementDeclarationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleElementDeclaration"


    // $ANTLR start "ruleElementDeclaration"
    // InternalLea.g:112:1: ruleElementDeclaration : ( ( rule__ElementDeclaration__Group__0 ) ) ;
    public final void ruleElementDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:116:2: ( ( ( rule__ElementDeclaration__Group__0 ) ) )
            // InternalLea.g:117:2: ( ( rule__ElementDeclaration__Group__0 ) )
            {
            // InternalLea.g:117:2: ( ( rule__ElementDeclaration__Group__0 ) )
            // InternalLea.g:118:3: ( rule__ElementDeclaration__Group__0 )
            {
             before(grammarAccess.getElementDeclarationAccess().getGroup()); 
            // InternalLea.g:119:3: ( rule__ElementDeclaration__Group__0 )
            // InternalLea.g:119:4: rule__ElementDeclaration__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElementDeclarationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElementDeclaration"


    // $ANTLR start "entryRuleGenericType"
    // InternalLea.g:128:1: entryRuleGenericType : ruleGenericType EOF ;
    public final void entryRuleGenericType() throws RecognitionException {
        try {
            // InternalLea.g:129:1: ( ruleGenericType EOF )
            // InternalLea.g:130:1: ruleGenericType EOF
            {
             before(grammarAccess.getGenericTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleGenericType();

            state._fsp--;

             after(grammarAccess.getGenericTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGenericType"


    // $ANTLR start "ruleGenericType"
    // InternalLea.g:137:1: ruleGenericType : ( ( rule__GenericType__Alternatives ) ) ;
    public final void ruleGenericType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:141:2: ( ( ( rule__GenericType__Alternatives ) ) )
            // InternalLea.g:142:2: ( ( rule__GenericType__Alternatives ) )
            {
            // InternalLea.g:142:2: ( ( rule__GenericType__Alternatives ) )
            // InternalLea.g:143:3: ( rule__GenericType__Alternatives )
            {
             before(grammarAccess.getGenericTypeAccess().getAlternatives()); 
            // InternalLea.g:144:3: ( rule__GenericType__Alternatives )
            // InternalLea.g:144:4: rule__GenericType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__GenericType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getGenericTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGenericType"


    // $ANTLR start "entryRuleSetDefinition"
    // InternalLea.g:153:1: entryRuleSetDefinition : ruleSetDefinition EOF ;
    public final void entryRuleSetDefinition() throws RecognitionException {
        try {
            // InternalLea.g:154:1: ( ruleSetDefinition EOF )
            // InternalLea.g:155:1: ruleSetDefinition EOF
            {
             before(grammarAccess.getSetDefinitionRule()); 
            pushFollow(FOLLOW_1);
            ruleSetDefinition();

            state._fsp--;

             after(grammarAccess.getSetDefinitionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSetDefinition"


    // $ANTLR start "ruleSetDefinition"
    // InternalLea.g:162:1: ruleSetDefinition : ( ( rule__SetDefinition__Group__0 ) ) ;
    public final void ruleSetDefinition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:166:2: ( ( ( rule__SetDefinition__Group__0 ) ) )
            // InternalLea.g:167:2: ( ( rule__SetDefinition__Group__0 ) )
            {
            // InternalLea.g:167:2: ( ( rule__SetDefinition__Group__0 ) )
            // InternalLea.g:168:3: ( rule__SetDefinition__Group__0 )
            {
             before(grammarAccess.getSetDefinitionAccess().getGroup()); 
            // InternalLea.g:169:3: ( rule__SetDefinition__Group__0 )
            // InternalLea.g:169:4: rule__SetDefinition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SetDefinition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSetDefinitionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSetDefinition"


    // $ANTLR start "entryRuleIteration"
    // InternalLea.g:178:1: entryRuleIteration : ruleIteration EOF ;
    public final void entryRuleIteration() throws RecognitionException {
        try {
            // InternalLea.g:179:1: ( ruleIteration EOF )
            // InternalLea.g:180:1: ruleIteration EOF
            {
             before(grammarAccess.getIterationRule()); 
            pushFollow(FOLLOW_1);
            ruleIteration();

            state._fsp--;

             after(grammarAccess.getIterationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIteration"


    // $ANTLR start "ruleIteration"
    // InternalLea.g:187:1: ruleIteration : ( ( rule__Iteration__Group__0 ) ) ;
    public final void ruleIteration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:191:2: ( ( ( rule__Iteration__Group__0 ) ) )
            // InternalLea.g:192:2: ( ( rule__Iteration__Group__0 ) )
            {
            // InternalLea.g:192:2: ( ( rule__Iteration__Group__0 ) )
            // InternalLea.g:193:3: ( rule__Iteration__Group__0 )
            {
             before(grammarAccess.getIterationAccess().getGroup()); 
            // InternalLea.g:194:3: ( rule__Iteration__Group__0 )
            // InternalLea.g:194:4: rule__Iteration__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Iteration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIterationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIteration"


    // $ANTLR start "entryRuleAssignment"
    // InternalLea.g:203:1: entryRuleAssignment : ruleAssignment EOF ;
    public final void entryRuleAssignment() throws RecognitionException {
        try {
            // InternalLea.g:204:1: ( ruleAssignment EOF )
            // InternalLea.g:205:1: ruleAssignment EOF
            {
             before(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getAssignmentRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalLea.g:212:1: ruleAssignment : ( ( rule__Assignment__Group__0 ) ) ;
    public final void ruleAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:216:2: ( ( ( rule__Assignment__Group__0 ) ) )
            // InternalLea.g:217:2: ( ( rule__Assignment__Group__0 ) )
            {
            // InternalLea.g:217:2: ( ( rule__Assignment__Group__0 ) )
            // InternalLea.g:218:3: ( rule__Assignment__Group__0 )
            {
             before(grammarAccess.getAssignmentAccess().getGroup()); 
            // InternalLea.g:219:3: ( rule__Assignment__Group__0 )
            // InternalLea.g:219:4: rule__Assignment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleOperation"
    // InternalLea.g:228:1: entryRuleOperation : ruleOperation EOF ;
    public final void entryRuleOperation() throws RecognitionException {
        try {
            // InternalLea.g:229:1: ( ruleOperation EOF )
            // InternalLea.g:230:1: ruleOperation EOF
            {
             before(grammarAccess.getOperationRule()); 
            pushFollow(FOLLOW_1);
            ruleOperation();

            state._fsp--;

             after(grammarAccess.getOperationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation"


    // $ANTLR start "ruleOperation"
    // InternalLea.g:237:1: ruleOperation : ( ( rule__Operation__Group__0 ) ) ;
    public final void ruleOperation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:241:2: ( ( ( rule__Operation__Group__0 ) ) )
            // InternalLea.g:242:2: ( ( rule__Operation__Group__0 ) )
            {
            // InternalLea.g:242:2: ( ( rule__Operation__Group__0 ) )
            // InternalLea.g:243:3: ( rule__Operation__Group__0 )
            {
             before(grammarAccess.getOperationAccess().getGroup()); 
            // InternalLea.g:244:3: ( rule__Operation__Group__0 )
            // InternalLea.g:244:4: rule__Operation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Operation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation"


    // $ANTLR start "entryRuleCall"
    // InternalLea.g:253:1: entryRuleCall : ruleCall EOF ;
    public final void entryRuleCall() throws RecognitionException {
        try {
            // InternalLea.g:254:1: ( ruleCall EOF )
            // InternalLea.g:255:1: ruleCall EOF
            {
             before(grammarAccess.getCallRule()); 
            pushFollow(FOLLOW_1);
            ruleCall();

            state._fsp--;

             after(grammarAccess.getCallRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCall"


    // $ANTLR start "ruleCall"
    // InternalLea.g:262:1: ruleCall : ( ( rule__Call__Group__0 ) ) ;
    public final void ruleCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:266:2: ( ( ( rule__Call__Group__0 ) ) )
            // InternalLea.g:267:2: ( ( rule__Call__Group__0 ) )
            {
            // InternalLea.g:267:2: ( ( rule__Call__Group__0 ) )
            // InternalLea.g:268:3: ( rule__Call__Group__0 )
            {
             before(grammarAccess.getCallAccess().getGroup()); 
            // InternalLea.g:269:3: ( rule__Call__Group__0 )
            // InternalLea.g:269:4: rule__Call__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Call__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCallAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCall"


    // $ANTLR start "entryRuleParameterList"
    // InternalLea.g:278:1: entryRuleParameterList : ruleParameterList EOF ;
    public final void entryRuleParameterList() throws RecognitionException {
        try {
            // InternalLea.g:279:1: ( ruleParameterList EOF )
            // InternalLea.g:280:1: ruleParameterList EOF
            {
             before(grammarAccess.getParameterListRule()); 
            pushFollow(FOLLOW_1);
            ruleParameterList();

            state._fsp--;

             after(grammarAccess.getParameterListRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParameterList"


    // $ANTLR start "ruleParameterList"
    // InternalLea.g:287:1: ruleParameterList : ( ( rule__ParameterList__Group__0 ) ) ;
    public final void ruleParameterList() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:291:2: ( ( ( rule__ParameterList__Group__0 ) ) )
            // InternalLea.g:292:2: ( ( rule__ParameterList__Group__0 ) )
            {
            // InternalLea.g:292:2: ( ( rule__ParameterList__Group__0 ) )
            // InternalLea.g:293:3: ( rule__ParameterList__Group__0 )
            {
             before(grammarAccess.getParameterListAccess().getGroup()); 
            // InternalLea.g:294:3: ( rule__ParameterList__Group__0 )
            // InternalLea.g:294:4: rule__ParameterList__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getParameterListAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameterList"


    // $ANTLR start "entryRuleParameter"
    // InternalLea.g:303:1: entryRuleParameter : ruleParameter EOF ;
    public final void entryRuleParameter() throws RecognitionException {
        try {
            // InternalLea.g:304:1: ( ruleParameter EOF )
            // InternalLea.g:305:1: ruleParameter EOF
            {
             before(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getParameterRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalLea.g:312:1: ruleParameter : ( ( rule__Parameter__Alternatives ) ) ;
    public final void ruleParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:316:2: ( ( ( rule__Parameter__Alternatives ) ) )
            // InternalLea.g:317:2: ( ( rule__Parameter__Alternatives ) )
            {
            // InternalLea.g:317:2: ( ( rule__Parameter__Alternatives ) )
            // InternalLea.g:318:3: ( rule__Parameter__Alternatives )
            {
             before(grammarAccess.getParameterAccess().getAlternatives()); 
            // InternalLea.g:319:3: ( rule__Parameter__Alternatives )
            // InternalLea.g:319:4: rule__Parameter__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "rule__AnalysisDefinition__Alternatives_1"
    // InternalLea.g:327:1: rule__AnalysisDefinition__Alternatives_1 : ( ( ( rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0 ) ) | ( ( rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1 ) ) );
    public final void rule__AnalysisDefinition__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:331:1: ( ( ( rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0 ) ) | ( ( rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=11 && LA1_0<=13)) ) {
                alt1=1;
            }
            else if ( (LA1_0==14) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalLea.g:332:2: ( ( rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0 ) )
                    {
                    // InternalLea.g:332:2: ( ( rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0 ) )
                    // InternalLea.g:333:3: ( rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0 )
                    {
                     before(grammarAccess.getAnalysisDefinitionAccess().getElementDeclarationsAssignment_1_0()); 
                    // InternalLea.g:334:3: ( rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0 )
                    // InternalLea.g:334:4: rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAnalysisDefinitionAccess().getElementDeclarationsAssignment_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLea.g:338:2: ( ( rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1 ) )
                    {
                    // InternalLea.g:338:2: ( ( rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1 ) )
                    // InternalLea.g:339:3: ( rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1 )
                    {
                     before(grammarAccess.getAnalysisDefinitionAccess().getChangeIdentifierAssignmentsAssignment_1_1()); 
                    // InternalLea.g:340:3: ( rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1 )
                    // InternalLea.g:340:4: rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getAnalysisDefinitionAccess().getChangeIdentifierAssignmentsAssignment_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnalysisDefinition__Alternatives_1"


    // $ANTLR start "rule__GenericType__Alternatives"
    // InternalLea.g:348:1: rule__GenericType__Alternatives : ( ( 'Artifact' ) | ( 'Fragment' ) | ( 'Result' ) );
    public final void rule__GenericType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:352:1: ( ( 'Artifact' ) | ( 'Fragment' ) | ( 'Result' ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt2=1;
                }
                break;
            case 12:
                {
                alt2=2;
                }
                break;
            case 13:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalLea.g:353:2: ( 'Artifact' )
                    {
                    // InternalLea.g:353:2: ( 'Artifact' )
                    // InternalLea.g:354:3: 'Artifact'
                    {
                     before(grammarAccess.getGenericTypeAccess().getArtifactKeyword_0()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getGenericTypeAccess().getArtifactKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLea.g:359:2: ( 'Fragment' )
                    {
                    // InternalLea.g:359:2: ( 'Fragment' )
                    // InternalLea.g:360:3: 'Fragment'
                    {
                     before(grammarAccess.getGenericTypeAccess().getFragmentKeyword_1()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getGenericTypeAccess().getFragmentKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalLea.g:365:2: ( 'Result' )
                    {
                    // InternalLea.g:365:2: ( 'Result' )
                    // InternalLea.g:366:3: 'Result'
                    {
                     before(grammarAccess.getGenericTypeAccess().getResultKeyword_2()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getGenericTypeAccess().getResultKeyword_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__GenericType__Alternatives"


    // $ANTLR start "rule__Assignment__Alternatives_1"
    // InternalLea.g:375:1: rule__Assignment__Alternatives_1 : ( ( ( rule__Assignment__ElementAssignment_1_0 ) ) | ( ( rule__Assignment__OperationAssignment_1_1 ) ) );
    public final void rule__Assignment__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:379:1: ( ( ( rule__Assignment__ElementAssignment_1_0 ) ) | ( ( rule__Assignment__OperationAssignment_1_1 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                int LA3_1 = input.LA(2);

                if ( ((LA3_1>=24 && LA3_1<=25)) ) {
                    alt3=2;
                }
                else if ( (LA3_1==EOF||(LA3_1>=11 && LA3_1<=14)||LA3_1==16) ) {
                    alt3=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalLea.g:380:2: ( ( rule__Assignment__ElementAssignment_1_0 ) )
                    {
                    // InternalLea.g:380:2: ( ( rule__Assignment__ElementAssignment_1_0 ) )
                    // InternalLea.g:381:3: ( rule__Assignment__ElementAssignment_1_0 )
                    {
                     before(grammarAccess.getAssignmentAccess().getElementAssignment_1_0()); 
                    // InternalLea.g:382:3: ( rule__Assignment__ElementAssignment_1_0 )
                    // InternalLea.g:382:4: rule__Assignment__ElementAssignment_1_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Assignment__ElementAssignment_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAssignmentAccess().getElementAssignment_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLea.g:386:2: ( ( rule__Assignment__OperationAssignment_1_1 ) )
                    {
                    // InternalLea.g:386:2: ( ( rule__Assignment__OperationAssignment_1_1 ) )
                    // InternalLea.g:387:3: ( rule__Assignment__OperationAssignment_1_1 )
                    {
                     before(grammarAccess.getAssignmentAccess().getOperationAssignment_1_1()); 
                    // InternalLea.g:388:3: ( rule__Assignment__OperationAssignment_1_1 )
                    // InternalLea.g:388:4: rule__Assignment__OperationAssignment_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Assignment__OperationAssignment_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getAssignmentAccess().getOperationAssignment_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Alternatives_1"


    // $ANTLR start "rule__Parameter__Alternatives"
    // InternalLea.g:396:1: rule__Parameter__Alternatives : ( ( ( rule__Parameter__TextAssignment_0 ) ) | ( ( rule__Parameter__ElementAssignment_1 ) ) | ( ( rule__Parameter__OperationAssignment_2 ) ) );
    public final void rule__Parameter__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:400:1: ( ( ( rule__Parameter__TextAssignment_0 ) ) | ( ( rule__Parameter__ElementAssignment_1 ) ) | ( ( rule__Parameter__OperationAssignment_2 ) ) )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_STRING) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==EOF||LA4_2==17||LA4_2==26) ) {
                    alt4=2;
                }
                else if ( ((LA4_2>=24 && LA4_2<=25)) ) {
                    alt4=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalLea.g:401:2: ( ( rule__Parameter__TextAssignment_0 ) )
                    {
                    // InternalLea.g:401:2: ( ( rule__Parameter__TextAssignment_0 ) )
                    // InternalLea.g:402:3: ( rule__Parameter__TextAssignment_0 )
                    {
                     before(grammarAccess.getParameterAccess().getTextAssignment_0()); 
                    // InternalLea.g:403:3: ( rule__Parameter__TextAssignment_0 )
                    // InternalLea.g:403:4: rule__Parameter__TextAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Parameter__TextAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getParameterAccess().getTextAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLea.g:407:2: ( ( rule__Parameter__ElementAssignment_1 ) )
                    {
                    // InternalLea.g:407:2: ( ( rule__Parameter__ElementAssignment_1 ) )
                    // InternalLea.g:408:3: ( rule__Parameter__ElementAssignment_1 )
                    {
                     before(grammarAccess.getParameterAccess().getElementAssignment_1()); 
                    // InternalLea.g:409:3: ( rule__Parameter__ElementAssignment_1 )
                    // InternalLea.g:409:4: rule__Parameter__ElementAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Parameter__ElementAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getParameterAccess().getElementAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalLea.g:413:2: ( ( rule__Parameter__OperationAssignment_2 ) )
                    {
                    // InternalLea.g:413:2: ( ( rule__Parameter__OperationAssignment_2 ) )
                    // InternalLea.g:414:3: ( rule__Parameter__OperationAssignment_2 )
                    {
                     before(grammarAccess.getParameterAccess().getOperationAssignment_2()); 
                    // InternalLea.g:415:3: ( rule__Parameter__OperationAssignment_2 )
                    // InternalLea.g:415:4: rule__Parameter__OperationAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Parameter__OperationAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getParameterAccess().getOperationAssignment_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Alternatives"


    // $ANTLR start "rule__AnalysisDefinition__Group__0"
    // InternalLea.g:423:1: rule__AnalysisDefinition__Group__0 : rule__AnalysisDefinition__Group__0__Impl rule__AnalysisDefinition__Group__1 ;
    public final void rule__AnalysisDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:427:1: ( rule__AnalysisDefinition__Group__0__Impl rule__AnalysisDefinition__Group__1 )
            // InternalLea.g:428:2: rule__AnalysisDefinition__Group__0__Impl rule__AnalysisDefinition__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__AnalysisDefinition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AnalysisDefinition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnalysisDefinition__Group__0"


    // $ANTLR start "rule__AnalysisDefinition__Group__0__Impl"
    // InternalLea.g:435:1: rule__AnalysisDefinition__Group__0__Impl : ( () ) ;
    public final void rule__AnalysisDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:439:1: ( ( () ) )
            // InternalLea.g:440:1: ( () )
            {
            // InternalLea.g:440:1: ( () )
            // InternalLea.g:441:2: ()
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getAnalysisDefinitionAction_0()); 
            // InternalLea.g:442:2: ()
            // InternalLea.g:442:3: 
            {
            }

             after(grammarAccess.getAnalysisDefinitionAccess().getAnalysisDefinitionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnalysisDefinition__Group__0__Impl"


    // $ANTLR start "rule__AnalysisDefinition__Group__1"
    // InternalLea.g:450:1: rule__AnalysisDefinition__Group__1 : rule__AnalysisDefinition__Group__1__Impl ;
    public final void rule__AnalysisDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:454:1: ( rule__AnalysisDefinition__Group__1__Impl )
            // InternalLea.g:455:2: rule__AnalysisDefinition__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AnalysisDefinition__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnalysisDefinition__Group__1"


    // $ANTLR start "rule__AnalysisDefinition__Group__1__Impl"
    // InternalLea.g:461:1: rule__AnalysisDefinition__Group__1__Impl : ( ( rule__AnalysisDefinition__Alternatives_1 )* ) ;
    public final void rule__AnalysisDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:465:1: ( ( ( rule__AnalysisDefinition__Alternatives_1 )* ) )
            // InternalLea.g:466:1: ( ( rule__AnalysisDefinition__Alternatives_1 )* )
            {
            // InternalLea.g:466:1: ( ( rule__AnalysisDefinition__Alternatives_1 )* )
            // InternalLea.g:467:2: ( rule__AnalysisDefinition__Alternatives_1 )*
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getAlternatives_1()); 
            // InternalLea.g:468:2: ( rule__AnalysisDefinition__Alternatives_1 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=11 && LA5_0<=14)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalLea.g:468:3: rule__AnalysisDefinition__Alternatives_1
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__AnalysisDefinition__Alternatives_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getAnalysisDefinitionAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnalysisDefinition__Group__1__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__0"
    // InternalLea.g:477:1: rule__ChangeIdentifierAssignment__Group__0 : rule__ChangeIdentifierAssignment__Group__0__Impl rule__ChangeIdentifierAssignment__Group__1 ;
    public final void rule__ChangeIdentifierAssignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:481:1: ( rule__ChangeIdentifierAssignment__Group__0__Impl rule__ChangeIdentifierAssignment__Group__1 )
            // InternalLea.g:482:2: rule__ChangeIdentifierAssignment__Group__0__Impl rule__ChangeIdentifierAssignment__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__ChangeIdentifierAssignment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__0"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__0__Impl"
    // InternalLea.g:489:1: rule__ChangeIdentifierAssignment__Group__0__Impl : ( 'assign' ) ;
    public final void rule__ChangeIdentifierAssignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:493:1: ( ( 'assign' ) )
            // InternalLea.g:494:1: ( 'assign' )
            {
            // InternalLea.g:494:1: ( 'assign' )
            // InternalLea.g:495:2: 'assign'
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getAssignKeyword_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getChangeIdentifierAssignmentAccess().getAssignKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__0__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__1"
    // InternalLea.g:504:1: rule__ChangeIdentifierAssignment__Group__1 : rule__ChangeIdentifierAssignment__Group__1__Impl rule__ChangeIdentifierAssignment__Group__2 ;
    public final void rule__ChangeIdentifierAssignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:508:1: ( rule__ChangeIdentifierAssignment__Group__1__Impl rule__ChangeIdentifierAssignment__Group__2 )
            // InternalLea.g:509:2: rule__ChangeIdentifierAssignment__Group__1__Impl rule__ChangeIdentifierAssignment__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__ChangeIdentifierAssignment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__1"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__1__Impl"
    // InternalLea.g:516:1: rule__ChangeIdentifierAssignment__Group__1__Impl : ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) ) ;
    public final void rule__ChangeIdentifierAssignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:520:1: ( ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) ) )
            // InternalLea.g:521:1: ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) )
            {
            // InternalLea.g:521:1: ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) )
            // InternalLea.g:522:2: ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 )
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getIdentifierAssignment_1()); 
            // InternalLea.g:523:2: ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 )
            // InternalLea.g:523:3: rule__ChangeIdentifierAssignment__IdentifierAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__IdentifierAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getChangeIdentifierAssignmentAccess().getIdentifierAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__1__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__2"
    // InternalLea.g:531:1: rule__ChangeIdentifierAssignment__Group__2 : rule__ChangeIdentifierAssignment__Group__2__Impl rule__ChangeIdentifierAssignment__Group__3 ;
    public final void rule__ChangeIdentifierAssignment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:535:1: ( rule__ChangeIdentifierAssignment__Group__2__Impl rule__ChangeIdentifierAssignment__Group__3 )
            // InternalLea.g:536:2: rule__ChangeIdentifierAssignment__Group__2__Impl rule__ChangeIdentifierAssignment__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__ChangeIdentifierAssignment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__2"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__2__Impl"
    // InternalLea.g:543:1: rule__ChangeIdentifierAssignment__Group__2__Impl : ( 'to' ) ;
    public final void rule__ChangeIdentifierAssignment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:547:1: ( ( 'to' ) )
            // InternalLea.g:548:1: ( 'to' )
            {
            // InternalLea.g:548:1: ( 'to' )
            // InternalLea.g:549:2: 'to'
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getToKeyword_2()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getChangeIdentifierAssignmentAccess().getToKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__2__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__3"
    // InternalLea.g:558:1: rule__ChangeIdentifierAssignment__Group__3 : rule__ChangeIdentifierAssignment__Group__3__Impl rule__ChangeIdentifierAssignment__Group__4 ;
    public final void rule__ChangeIdentifierAssignment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:562:1: ( rule__ChangeIdentifierAssignment__Group__3__Impl rule__ChangeIdentifierAssignment__Group__4 )
            // InternalLea.g:563:2: rule__ChangeIdentifierAssignment__Group__3__Impl rule__ChangeIdentifierAssignment__Group__4
            {
            pushFollow(FOLLOW_7);
            rule__ChangeIdentifierAssignment__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__3"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__3__Impl"
    // InternalLea.g:570:1: rule__ChangeIdentifierAssignment__Group__3__Impl : ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) ) ;
    public final void rule__ChangeIdentifierAssignment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:574:1: ( ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) ) )
            // InternalLea.g:575:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) )
            {
            // InternalLea.g:575:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) )
            // InternalLea.g:576:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 )
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsAssignment_3()); 
            // InternalLea.g:577:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 )
            // InternalLea.g:577:3: rule__ChangeIdentifierAssignment__ElementsAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__ElementsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__3__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__4"
    // InternalLea.g:585:1: rule__ChangeIdentifierAssignment__Group__4 : rule__ChangeIdentifierAssignment__Group__4__Impl rule__ChangeIdentifierAssignment__Group__5 ;
    public final void rule__ChangeIdentifierAssignment__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:589:1: ( rule__ChangeIdentifierAssignment__Group__4__Impl rule__ChangeIdentifierAssignment__Group__5 )
            // InternalLea.g:590:2: rule__ChangeIdentifierAssignment__Group__4__Impl rule__ChangeIdentifierAssignment__Group__5
            {
            pushFollow(FOLLOW_7);
            rule__ChangeIdentifierAssignment__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__4"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__4__Impl"
    // InternalLea.g:597:1: rule__ChangeIdentifierAssignment__Group__4__Impl : ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* ) ;
    public final void rule__ChangeIdentifierAssignment__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:601:1: ( ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* ) )
            // InternalLea.g:602:1: ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* )
            {
            // InternalLea.g:602:1: ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* )
            // InternalLea.g:603:2: ( rule__ChangeIdentifierAssignment__Group_4__0 )*
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getGroup_4()); 
            // InternalLea.g:604:2: ( rule__ChangeIdentifierAssignment__Group_4__0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==17) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalLea.g:604:3: rule__ChangeIdentifierAssignment__Group_4__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__ChangeIdentifierAssignment__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getChangeIdentifierAssignmentAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__4__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__5"
    // InternalLea.g:612:1: rule__ChangeIdentifierAssignment__Group__5 : rule__ChangeIdentifierAssignment__Group__5__Impl ;
    public final void rule__ChangeIdentifierAssignment__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:616:1: ( rule__ChangeIdentifierAssignment__Group__5__Impl )
            // InternalLea.g:617:2: rule__ChangeIdentifierAssignment__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__5"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group__5__Impl"
    // InternalLea.g:623:1: rule__ChangeIdentifierAssignment__Group__5__Impl : ( ( ';' )? ) ;
    public final void rule__ChangeIdentifierAssignment__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:627:1: ( ( ( ';' )? ) )
            // InternalLea.g:628:1: ( ( ';' )? )
            {
            // InternalLea.g:628:1: ( ( ';' )? )
            // InternalLea.g:629:2: ( ';' )?
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getSemicolonKeyword_5()); 
            // InternalLea.g:630:2: ( ';' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==16) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalLea.g:630:3: ';'
                    {
                    match(input,16,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getChangeIdentifierAssignmentAccess().getSemicolonKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group__5__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group_4__0"
    // InternalLea.g:639:1: rule__ChangeIdentifierAssignment__Group_4__0 : rule__ChangeIdentifierAssignment__Group_4__0__Impl rule__ChangeIdentifierAssignment__Group_4__1 ;
    public final void rule__ChangeIdentifierAssignment__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:643:1: ( rule__ChangeIdentifierAssignment__Group_4__0__Impl rule__ChangeIdentifierAssignment__Group_4__1 )
            // InternalLea.g:644:2: rule__ChangeIdentifierAssignment__Group_4__0__Impl rule__ChangeIdentifierAssignment__Group_4__1
            {
            pushFollow(FOLLOW_5);
            rule__ChangeIdentifierAssignment__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group_4__0"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group_4__0__Impl"
    // InternalLea.g:651:1: rule__ChangeIdentifierAssignment__Group_4__0__Impl : ( ',' ) ;
    public final void rule__ChangeIdentifierAssignment__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:655:1: ( ( ',' ) )
            // InternalLea.g:656:1: ( ',' )
            {
            // InternalLea.g:656:1: ( ',' )
            // InternalLea.g:657:2: ','
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getCommaKeyword_4_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getChangeIdentifierAssignmentAccess().getCommaKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group_4__0__Impl"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group_4__1"
    // InternalLea.g:666:1: rule__ChangeIdentifierAssignment__Group_4__1 : rule__ChangeIdentifierAssignment__Group_4__1__Impl ;
    public final void rule__ChangeIdentifierAssignment__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:670:1: ( rule__ChangeIdentifierAssignment__Group_4__1__Impl )
            // InternalLea.g:671:2: rule__ChangeIdentifierAssignment__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group_4__1"


    // $ANTLR start "rule__ChangeIdentifierAssignment__Group_4__1__Impl"
    // InternalLea.g:677:1: rule__ChangeIdentifierAssignment__Group_4__1__Impl : ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) ) ;
    public final void rule__ChangeIdentifierAssignment__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:681:1: ( ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) ) )
            // InternalLea.g:682:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) )
            {
            // InternalLea.g:682:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) )
            // InternalLea.g:683:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 )
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsAssignment_4_1()); 
            // InternalLea.g:684:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 )
            // InternalLea.g:684:3: rule__ChangeIdentifierAssignment__ElementsAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__ChangeIdentifierAssignment__ElementsAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__Group_4__1__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__0"
    // InternalLea.g:693:1: rule__ElementDeclaration__Group__0 : rule__ElementDeclaration__Group__0__Impl rule__ElementDeclaration__Group__1 ;
    public final void rule__ElementDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:697:1: ( rule__ElementDeclaration__Group__0__Impl rule__ElementDeclaration__Group__1 )
            // InternalLea.g:698:2: rule__ElementDeclaration__Group__0__Impl rule__ElementDeclaration__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ElementDeclaration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__0"


    // $ANTLR start "rule__ElementDeclaration__Group__0__Impl"
    // InternalLea.g:705:1: rule__ElementDeclaration__Group__0__Impl : ( ( rule__ElementDeclaration__GenericTypAssignment_0 ) ) ;
    public final void rule__ElementDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:709:1: ( ( ( rule__ElementDeclaration__GenericTypAssignment_0 ) ) )
            // InternalLea.g:710:1: ( ( rule__ElementDeclaration__GenericTypAssignment_0 ) )
            {
            // InternalLea.g:710:1: ( ( rule__ElementDeclaration__GenericTypAssignment_0 ) )
            // InternalLea.g:711:2: ( rule__ElementDeclaration__GenericTypAssignment_0 )
            {
             before(grammarAccess.getElementDeclarationAccess().getGenericTypAssignment_0()); 
            // InternalLea.g:712:2: ( rule__ElementDeclaration__GenericTypAssignment_0 )
            // InternalLea.g:712:3: rule__ElementDeclaration__GenericTypAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__GenericTypAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getElementDeclarationAccess().getGenericTypAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__0__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__1"
    // InternalLea.g:720:1: rule__ElementDeclaration__Group__1 : rule__ElementDeclaration__Group__1__Impl rule__ElementDeclaration__Group__2 ;
    public final void rule__ElementDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:724:1: ( rule__ElementDeclaration__Group__1__Impl rule__ElementDeclaration__Group__2 )
            // InternalLea.g:725:2: rule__ElementDeclaration__Group__1__Impl rule__ElementDeclaration__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__ElementDeclaration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__1"


    // $ANTLR start "rule__ElementDeclaration__Group__1__Impl"
    // InternalLea.g:732:1: rule__ElementDeclaration__Group__1__Impl : ( '<' ) ;
    public final void rule__ElementDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:736:1: ( ( '<' ) )
            // InternalLea.g:737:1: ( '<' )
            {
            // InternalLea.g:737:1: ( '<' )
            // InternalLea.g:738:2: '<'
            {
             before(grammarAccess.getElementDeclarationAccess().getLessThanSignKeyword_1()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getElementDeclarationAccess().getLessThanSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__1__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__2"
    // InternalLea.g:747:1: rule__ElementDeclaration__Group__2 : rule__ElementDeclaration__Group__2__Impl rule__ElementDeclaration__Group__3 ;
    public final void rule__ElementDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:751:1: ( rule__ElementDeclaration__Group__2__Impl rule__ElementDeclaration__Group__3 )
            // InternalLea.g:752:2: rule__ElementDeclaration__Group__2__Impl rule__ElementDeclaration__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__ElementDeclaration__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__2"


    // $ANTLR start "rule__ElementDeclaration__Group__2__Impl"
    // InternalLea.g:759:1: rule__ElementDeclaration__Group__2__Impl : ( ( rule__ElementDeclaration__ParameterTypeAssignment_2 ) ) ;
    public final void rule__ElementDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:763:1: ( ( ( rule__ElementDeclaration__ParameterTypeAssignment_2 ) ) )
            // InternalLea.g:764:1: ( ( rule__ElementDeclaration__ParameterTypeAssignment_2 ) )
            {
            // InternalLea.g:764:1: ( ( rule__ElementDeclaration__ParameterTypeAssignment_2 ) )
            // InternalLea.g:765:2: ( rule__ElementDeclaration__ParameterTypeAssignment_2 )
            {
             before(grammarAccess.getElementDeclarationAccess().getParameterTypeAssignment_2()); 
            // InternalLea.g:766:2: ( rule__ElementDeclaration__ParameterTypeAssignment_2 )
            // InternalLea.g:766:3: rule__ElementDeclaration__ParameterTypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__ParameterTypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getElementDeclarationAccess().getParameterTypeAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__2__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__3"
    // InternalLea.g:774:1: rule__ElementDeclaration__Group__3 : rule__ElementDeclaration__Group__3__Impl rule__ElementDeclaration__Group__4 ;
    public final void rule__ElementDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:778:1: ( rule__ElementDeclaration__Group__3__Impl rule__ElementDeclaration__Group__4 )
            // InternalLea.g:779:2: rule__ElementDeclaration__Group__3__Impl rule__ElementDeclaration__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__ElementDeclaration__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__3"


    // $ANTLR start "rule__ElementDeclaration__Group__3__Impl"
    // InternalLea.g:786:1: rule__ElementDeclaration__Group__3__Impl : ( '>' ) ;
    public final void rule__ElementDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:790:1: ( ( '>' ) )
            // InternalLea.g:791:1: ( '>' )
            {
            // InternalLea.g:791:1: ( '>' )
            // InternalLea.g:792:2: '>'
            {
             before(grammarAccess.getElementDeclarationAccess().getGreaterThanSignKeyword_3()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getElementDeclarationAccess().getGreaterThanSignKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__3__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__4"
    // InternalLea.g:801:1: rule__ElementDeclaration__Group__4 : rule__ElementDeclaration__Group__4__Impl rule__ElementDeclaration__Group__5 ;
    public final void rule__ElementDeclaration__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:805:1: ( rule__ElementDeclaration__Group__4__Impl rule__ElementDeclaration__Group__5 )
            // InternalLea.g:806:2: rule__ElementDeclaration__Group__4__Impl rule__ElementDeclaration__Group__5
            {
            pushFollow(FOLLOW_11);
            rule__ElementDeclaration__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__4"


    // $ANTLR start "rule__ElementDeclaration__Group__4__Impl"
    // InternalLea.g:813:1: rule__ElementDeclaration__Group__4__Impl : ( ( rule__ElementDeclaration__SetAssignment_4 )? ) ;
    public final void rule__ElementDeclaration__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:817:1: ( ( ( rule__ElementDeclaration__SetAssignment_4 )? ) )
            // InternalLea.g:818:1: ( ( rule__ElementDeclaration__SetAssignment_4 )? )
            {
            // InternalLea.g:818:1: ( ( rule__ElementDeclaration__SetAssignment_4 )? )
            // InternalLea.g:819:2: ( rule__ElementDeclaration__SetAssignment_4 )?
            {
             before(grammarAccess.getElementDeclarationAccess().getSetAssignment_4()); 
            // InternalLea.g:820:2: ( rule__ElementDeclaration__SetAssignment_4 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==20) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalLea.g:820:3: rule__ElementDeclaration__SetAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElementDeclaration__SetAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElementDeclarationAccess().getSetAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__4__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__5"
    // InternalLea.g:828:1: rule__ElementDeclaration__Group__5 : rule__ElementDeclaration__Group__5__Impl rule__ElementDeclaration__Group__6 ;
    public final void rule__ElementDeclaration__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:832:1: ( rule__ElementDeclaration__Group__5__Impl rule__ElementDeclaration__Group__6 )
            // InternalLea.g:833:2: rule__ElementDeclaration__Group__5__Impl rule__ElementDeclaration__Group__6
            {
            pushFollow(FOLLOW_12);
            rule__ElementDeclaration__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__5"


    // $ANTLR start "rule__ElementDeclaration__Group__5__Impl"
    // InternalLea.g:840:1: rule__ElementDeclaration__Group__5__Impl : ( ( rule__ElementDeclaration__NameAssignment_5 ) ) ;
    public final void rule__ElementDeclaration__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:844:1: ( ( ( rule__ElementDeclaration__NameAssignment_5 ) ) )
            // InternalLea.g:845:1: ( ( rule__ElementDeclaration__NameAssignment_5 ) )
            {
            // InternalLea.g:845:1: ( ( rule__ElementDeclaration__NameAssignment_5 ) )
            // InternalLea.g:846:2: ( rule__ElementDeclaration__NameAssignment_5 )
            {
             before(grammarAccess.getElementDeclarationAccess().getNameAssignment_5()); 
            // InternalLea.g:847:2: ( rule__ElementDeclaration__NameAssignment_5 )
            // InternalLea.g:847:3: rule__ElementDeclaration__NameAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__NameAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getElementDeclarationAccess().getNameAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__5__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__6"
    // InternalLea.g:855:1: rule__ElementDeclaration__Group__6 : rule__ElementDeclaration__Group__6__Impl rule__ElementDeclaration__Group__7 ;
    public final void rule__ElementDeclaration__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:859:1: ( rule__ElementDeclaration__Group__6__Impl rule__ElementDeclaration__Group__7 )
            // InternalLea.g:860:2: rule__ElementDeclaration__Group__6__Impl rule__ElementDeclaration__Group__7
            {
            pushFollow(FOLLOW_12);
            rule__ElementDeclaration__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__6"


    // $ANTLR start "rule__ElementDeclaration__Group__6__Impl"
    // InternalLea.g:867:1: rule__ElementDeclaration__Group__6__Impl : ( ( rule__ElementDeclaration__InitializationAssignment_6 )? ) ;
    public final void rule__ElementDeclaration__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:871:1: ( ( ( rule__ElementDeclaration__InitializationAssignment_6 )? ) )
            // InternalLea.g:872:1: ( ( rule__ElementDeclaration__InitializationAssignment_6 )? )
            {
            // InternalLea.g:872:1: ( ( rule__ElementDeclaration__InitializationAssignment_6 )? )
            // InternalLea.g:873:2: ( rule__ElementDeclaration__InitializationAssignment_6 )?
            {
             before(grammarAccess.getElementDeclarationAccess().getInitializationAssignment_6()); 
            // InternalLea.g:874:2: ( rule__ElementDeclaration__InitializationAssignment_6 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==23) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalLea.g:874:3: rule__ElementDeclaration__InitializationAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElementDeclaration__InitializationAssignment_6();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElementDeclarationAccess().getInitializationAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__6__Impl"


    // $ANTLR start "rule__ElementDeclaration__Group__7"
    // InternalLea.g:882:1: rule__ElementDeclaration__Group__7 : rule__ElementDeclaration__Group__7__Impl ;
    public final void rule__ElementDeclaration__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:886:1: ( rule__ElementDeclaration__Group__7__Impl )
            // InternalLea.g:887:2: rule__ElementDeclaration__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElementDeclaration__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__7"


    // $ANTLR start "rule__ElementDeclaration__Group__7__Impl"
    // InternalLea.g:893:1: rule__ElementDeclaration__Group__7__Impl : ( ( ';' )? ) ;
    public final void rule__ElementDeclaration__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:897:1: ( ( ( ';' )? ) )
            // InternalLea.g:898:1: ( ( ';' )? )
            {
            // InternalLea.g:898:1: ( ( ';' )? )
            // InternalLea.g:899:2: ( ';' )?
            {
             before(grammarAccess.getElementDeclarationAccess().getSemicolonKeyword_7()); 
            // InternalLea.g:900:2: ( ';' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==16) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalLea.g:900:3: ';'
                    {
                    match(input,16,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getElementDeclarationAccess().getSemicolonKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__Group__7__Impl"


    // $ANTLR start "rule__SetDefinition__Group__0"
    // InternalLea.g:909:1: rule__SetDefinition__Group__0 : rule__SetDefinition__Group__0__Impl rule__SetDefinition__Group__1 ;
    public final void rule__SetDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:913:1: ( rule__SetDefinition__Group__0__Impl rule__SetDefinition__Group__1 )
            // InternalLea.g:914:2: rule__SetDefinition__Group__0__Impl rule__SetDefinition__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__SetDefinition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetDefinition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__0"


    // $ANTLR start "rule__SetDefinition__Group__0__Impl"
    // InternalLea.g:921:1: rule__SetDefinition__Group__0__Impl : ( () ) ;
    public final void rule__SetDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:925:1: ( ( () ) )
            // InternalLea.g:926:1: ( () )
            {
            // InternalLea.g:926:1: ( () )
            // InternalLea.g:927:2: ()
            {
             before(grammarAccess.getSetDefinitionAccess().getSetDefinitionAction_0()); 
            // InternalLea.g:928:2: ()
            // InternalLea.g:928:3: 
            {
            }

             after(grammarAccess.getSetDefinitionAccess().getSetDefinitionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__0__Impl"


    // $ANTLR start "rule__SetDefinition__Group__1"
    // InternalLea.g:936:1: rule__SetDefinition__Group__1 : rule__SetDefinition__Group__1__Impl rule__SetDefinition__Group__2 ;
    public final void rule__SetDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:940:1: ( rule__SetDefinition__Group__1__Impl rule__SetDefinition__Group__2 )
            // InternalLea.g:941:2: rule__SetDefinition__Group__1__Impl rule__SetDefinition__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__SetDefinition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetDefinition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__1"


    // $ANTLR start "rule__SetDefinition__Group__1__Impl"
    // InternalLea.g:948:1: rule__SetDefinition__Group__1__Impl : ( '[' ) ;
    public final void rule__SetDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:952:1: ( ( '[' ) )
            // InternalLea.g:953:1: ( '[' )
            {
            // InternalLea.g:953:1: ( '[' )
            // InternalLea.g:954:2: '['
            {
             before(grammarAccess.getSetDefinitionAccess().getLeftSquareBracketKeyword_1()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getSetDefinitionAccess().getLeftSquareBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__1__Impl"


    // $ANTLR start "rule__SetDefinition__Group__2"
    // InternalLea.g:963:1: rule__SetDefinition__Group__2 : rule__SetDefinition__Group__2__Impl rule__SetDefinition__Group__3 ;
    public final void rule__SetDefinition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:967:1: ( rule__SetDefinition__Group__2__Impl rule__SetDefinition__Group__3 )
            // InternalLea.g:968:2: rule__SetDefinition__Group__2__Impl rule__SetDefinition__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__SetDefinition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetDefinition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__2"


    // $ANTLR start "rule__SetDefinition__Group__2__Impl"
    // InternalLea.g:975:1: rule__SetDefinition__Group__2__Impl : ( ( rule__SetDefinition__IterationAssignment_2 )? ) ;
    public final void rule__SetDefinition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:979:1: ( ( ( rule__SetDefinition__IterationAssignment_2 )? ) )
            // InternalLea.g:980:1: ( ( rule__SetDefinition__IterationAssignment_2 )? )
            {
            // InternalLea.g:980:1: ( ( rule__SetDefinition__IterationAssignment_2 )? )
            // InternalLea.g:981:2: ( rule__SetDefinition__IterationAssignment_2 )?
            {
             before(grammarAccess.getSetDefinitionAccess().getIterationAssignment_2()); 
            // InternalLea.g:982:2: ( rule__SetDefinition__IterationAssignment_2 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLea.g:982:3: rule__SetDefinition__IterationAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__SetDefinition__IterationAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSetDefinitionAccess().getIterationAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__2__Impl"


    // $ANTLR start "rule__SetDefinition__Group__3"
    // InternalLea.g:990:1: rule__SetDefinition__Group__3 : rule__SetDefinition__Group__3__Impl ;
    public final void rule__SetDefinition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:994:1: ( rule__SetDefinition__Group__3__Impl )
            // InternalLea.g:995:2: rule__SetDefinition__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SetDefinition__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__3"


    // $ANTLR start "rule__SetDefinition__Group__3__Impl"
    // InternalLea.g:1001:1: rule__SetDefinition__Group__3__Impl : ( ']' ) ;
    public final void rule__SetDefinition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1005:1: ( ( ']' ) )
            // InternalLea.g:1006:1: ( ']' )
            {
            // InternalLea.g:1006:1: ( ']' )
            // InternalLea.g:1007:2: ']'
            {
             before(grammarAccess.getSetDefinitionAccess().getRightSquareBracketKeyword_3()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getSetDefinitionAccess().getRightSquareBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__Group__3__Impl"


    // $ANTLR start "rule__Iteration__Group__0"
    // InternalLea.g:1017:1: rule__Iteration__Group__0 : rule__Iteration__Group__0__Impl rule__Iteration__Group__1 ;
    public final void rule__Iteration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1021:1: ( rule__Iteration__Group__0__Impl rule__Iteration__Group__1 )
            // InternalLea.g:1022:2: rule__Iteration__Group__0__Impl rule__Iteration__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__Iteration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Iteration__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__Group__0"


    // $ANTLR start "rule__Iteration__Group__0__Impl"
    // InternalLea.g:1029:1: rule__Iteration__Group__0__Impl : ( ( rule__Iteration__IteratorAssignment_0 ) ) ;
    public final void rule__Iteration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1033:1: ( ( ( rule__Iteration__IteratorAssignment_0 ) ) )
            // InternalLea.g:1034:1: ( ( rule__Iteration__IteratorAssignment_0 ) )
            {
            // InternalLea.g:1034:1: ( ( rule__Iteration__IteratorAssignment_0 ) )
            // InternalLea.g:1035:2: ( rule__Iteration__IteratorAssignment_0 )
            {
             before(grammarAccess.getIterationAccess().getIteratorAssignment_0()); 
            // InternalLea.g:1036:2: ( rule__Iteration__IteratorAssignment_0 )
            // InternalLea.g:1036:3: rule__Iteration__IteratorAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Iteration__IteratorAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getIterationAccess().getIteratorAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__Group__0__Impl"


    // $ANTLR start "rule__Iteration__Group__1"
    // InternalLea.g:1044:1: rule__Iteration__Group__1 : rule__Iteration__Group__1__Impl rule__Iteration__Group__2 ;
    public final void rule__Iteration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1048:1: ( rule__Iteration__Group__1__Impl rule__Iteration__Group__2 )
            // InternalLea.g:1049:2: rule__Iteration__Group__1__Impl rule__Iteration__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Iteration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Iteration__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__Group__1"


    // $ANTLR start "rule__Iteration__Group__1__Impl"
    // InternalLea.g:1056:1: rule__Iteration__Group__1__Impl : ( ':' ) ;
    public final void rule__Iteration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1060:1: ( ( ':' ) )
            // InternalLea.g:1061:1: ( ':' )
            {
            // InternalLea.g:1061:1: ( ':' )
            // InternalLea.g:1062:2: ':'
            {
             before(grammarAccess.getIterationAccess().getColonKeyword_1()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getIterationAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__Group__1__Impl"


    // $ANTLR start "rule__Iteration__Group__2"
    // InternalLea.g:1071:1: rule__Iteration__Group__2 : rule__Iteration__Group__2__Impl ;
    public final void rule__Iteration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1075:1: ( rule__Iteration__Group__2__Impl )
            // InternalLea.g:1076:2: rule__Iteration__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Iteration__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__Group__2"


    // $ANTLR start "rule__Iteration__Group__2__Impl"
    // InternalLea.g:1082:1: rule__Iteration__Group__2__Impl : ( ( rule__Iteration__IterableAssignment_2 ) ) ;
    public final void rule__Iteration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1086:1: ( ( ( rule__Iteration__IterableAssignment_2 ) ) )
            // InternalLea.g:1087:1: ( ( rule__Iteration__IterableAssignment_2 ) )
            {
            // InternalLea.g:1087:1: ( ( rule__Iteration__IterableAssignment_2 ) )
            // InternalLea.g:1088:2: ( rule__Iteration__IterableAssignment_2 )
            {
             before(grammarAccess.getIterationAccess().getIterableAssignment_2()); 
            // InternalLea.g:1089:2: ( rule__Iteration__IterableAssignment_2 )
            // InternalLea.g:1089:3: rule__Iteration__IterableAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Iteration__IterableAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getIterationAccess().getIterableAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__Group__2__Impl"


    // $ANTLR start "rule__Assignment__Group__0"
    // InternalLea.g:1098:1: rule__Assignment__Group__0 : rule__Assignment__Group__0__Impl rule__Assignment__Group__1 ;
    public final void rule__Assignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1102:1: ( rule__Assignment__Group__0__Impl rule__Assignment__Group__1 )
            // InternalLea.g:1103:2: rule__Assignment__Group__0__Impl rule__Assignment__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Assignment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__0"


    // $ANTLR start "rule__Assignment__Group__0__Impl"
    // InternalLea.g:1110:1: rule__Assignment__Group__0__Impl : ( '=' ) ;
    public final void rule__Assignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1114:1: ( ( '=' ) )
            // InternalLea.g:1115:1: ( '=' )
            {
            // InternalLea.g:1115:1: ( '=' )
            // InternalLea.g:1116:2: '='
            {
             before(grammarAccess.getAssignmentAccess().getEqualsSignKeyword_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getEqualsSignKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__0__Impl"


    // $ANTLR start "rule__Assignment__Group__1"
    // InternalLea.g:1125:1: rule__Assignment__Group__1 : rule__Assignment__Group__1__Impl ;
    public final void rule__Assignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1129:1: ( rule__Assignment__Group__1__Impl )
            // InternalLea.g:1130:2: rule__Assignment__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__1"


    // $ANTLR start "rule__Assignment__Group__1__Impl"
    // InternalLea.g:1136:1: rule__Assignment__Group__1__Impl : ( ( rule__Assignment__Alternatives_1 ) ) ;
    public final void rule__Assignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1140:1: ( ( ( rule__Assignment__Alternatives_1 ) ) )
            // InternalLea.g:1141:1: ( ( rule__Assignment__Alternatives_1 ) )
            {
            // InternalLea.g:1141:1: ( ( rule__Assignment__Alternatives_1 ) )
            // InternalLea.g:1142:2: ( rule__Assignment__Alternatives_1 )
            {
             before(grammarAccess.getAssignmentAccess().getAlternatives_1()); 
            // InternalLea.g:1143:2: ( rule__Assignment__Alternatives_1 )
            // InternalLea.g:1143:3: rule__Assignment__Alternatives_1
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Alternatives_1();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__1__Impl"


    // $ANTLR start "rule__Operation__Group__0"
    // InternalLea.g:1152:1: rule__Operation__Group__0 : rule__Operation__Group__0__Impl rule__Operation__Group__1 ;
    public final void rule__Operation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1156:1: ( rule__Operation__Group__0__Impl rule__Operation__Group__1 )
            // InternalLea.g:1157:2: rule__Operation__Group__0__Impl rule__Operation__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Operation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Operation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group__0"


    // $ANTLR start "rule__Operation__Group__0__Impl"
    // InternalLea.g:1164:1: rule__Operation__Group__0__Impl : ( ( rule__Operation__Group_0__0 )? ) ;
    public final void rule__Operation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1168:1: ( ( ( rule__Operation__Group_0__0 )? ) )
            // InternalLea.g:1169:1: ( ( rule__Operation__Group_0__0 )? )
            {
            // InternalLea.g:1169:1: ( ( rule__Operation__Group_0__0 )? )
            // InternalLea.g:1170:2: ( rule__Operation__Group_0__0 )?
            {
             before(grammarAccess.getOperationAccess().getGroup_0()); 
            // InternalLea.g:1171:2: ( rule__Operation__Group_0__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==24) ) {
                    alt12=1;
                }
            }
            switch (alt12) {
                case 1 :
                    // InternalLea.g:1171:3: rule__Operation__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Operation__Group_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOperationAccess().getGroup_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group__0__Impl"


    // $ANTLR start "rule__Operation__Group__1"
    // InternalLea.g:1179:1: rule__Operation__Group__1 : rule__Operation__Group__1__Impl rule__Operation__Group__2 ;
    public final void rule__Operation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1183:1: ( rule__Operation__Group__1__Impl rule__Operation__Group__2 )
            // InternalLea.g:1184:2: rule__Operation__Group__1__Impl rule__Operation__Group__2
            {
            pushFollow(FOLLOW_16);
            rule__Operation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Operation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group__1"


    // $ANTLR start "rule__Operation__Group__1__Impl"
    // InternalLea.g:1191:1: rule__Operation__Group__1__Impl : ( ( rule__Operation__CallAssignment_1 ) ) ;
    public final void rule__Operation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1195:1: ( ( ( rule__Operation__CallAssignment_1 ) ) )
            // InternalLea.g:1196:1: ( ( rule__Operation__CallAssignment_1 ) )
            {
            // InternalLea.g:1196:1: ( ( rule__Operation__CallAssignment_1 ) )
            // InternalLea.g:1197:2: ( rule__Operation__CallAssignment_1 )
            {
             before(grammarAccess.getOperationAccess().getCallAssignment_1()); 
            // InternalLea.g:1198:2: ( rule__Operation__CallAssignment_1 )
            // InternalLea.g:1198:3: rule__Operation__CallAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Operation__CallAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperationAccess().getCallAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group__1__Impl"


    // $ANTLR start "rule__Operation__Group__2"
    // InternalLea.g:1206:1: rule__Operation__Group__2 : rule__Operation__Group__2__Impl ;
    public final void rule__Operation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1210:1: ( rule__Operation__Group__2__Impl )
            // InternalLea.g:1211:2: rule__Operation__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Operation__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group__2"


    // $ANTLR start "rule__Operation__Group__2__Impl"
    // InternalLea.g:1217:1: rule__Operation__Group__2__Impl : ( ( rule__Operation__Group_2__0 )? ) ;
    public final void rule__Operation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1221:1: ( ( ( rule__Operation__Group_2__0 )? ) )
            // InternalLea.g:1222:1: ( ( rule__Operation__Group_2__0 )? )
            {
            // InternalLea.g:1222:1: ( ( rule__Operation__Group_2__0 )? )
            // InternalLea.g:1223:2: ( rule__Operation__Group_2__0 )?
            {
             before(grammarAccess.getOperationAccess().getGroup_2()); 
            // InternalLea.g:1224:2: ( rule__Operation__Group_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==24) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalLea.g:1224:3: rule__Operation__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Operation__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOperationAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group__2__Impl"


    // $ANTLR start "rule__Operation__Group_0__0"
    // InternalLea.g:1233:1: rule__Operation__Group_0__0 : rule__Operation__Group_0__0__Impl rule__Operation__Group_0__1 ;
    public final void rule__Operation__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1237:1: ( rule__Operation__Group_0__0__Impl rule__Operation__Group_0__1 )
            // InternalLea.g:1238:2: rule__Operation__Group_0__0__Impl rule__Operation__Group_0__1
            {
            pushFollow(FOLLOW_16);
            rule__Operation__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Operation__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_0__0"


    // $ANTLR start "rule__Operation__Group_0__0__Impl"
    // InternalLea.g:1245:1: rule__Operation__Group_0__0__Impl : ( ( rule__Operation__ElementAssignment_0_0 ) ) ;
    public final void rule__Operation__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1249:1: ( ( ( rule__Operation__ElementAssignment_0_0 ) ) )
            // InternalLea.g:1250:1: ( ( rule__Operation__ElementAssignment_0_0 ) )
            {
            // InternalLea.g:1250:1: ( ( rule__Operation__ElementAssignment_0_0 ) )
            // InternalLea.g:1251:2: ( rule__Operation__ElementAssignment_0_0 )
            {
             before(grammarAccess.getOperationAccess().getElementAssignment_0_0()); 
            // InternalLea.g:1252:2: ( rule__Operation__ElementAssignment_0_0 )
            // InternalLea.g:1252:3: rule__Operation__ElementAssignment_0_0
            {
            pushFollow(FOLLOW_2);
            rule__Operation__ElementAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getOperationAccess().getElementAssignment_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_0__0__Impl"


    // $ANTLR start "rule__Operation__Group_0__1"
    // InternalLea.g:1260:1: rule__Operation__Group_0__1 : rule__Operation__Group_0__1__Impl ;
    public final void rule__Operation__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1264:1: ( rule__Operation__Group_0__1__Impl )
            // InternalLea.g:1265:2: rule__Operation__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Operation__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_0__1"


    // $ANTLR start "rule__Operation__Group_0__1__Impl"
    // InternalLea.g:1271:1: rule__Operation__Group_0__1__Impl : ( '.' ) ;
    public final void rule__Operation__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1275:1: ( ( '.' ) )
            // InternalLea.g:1276:1: ( '.' )
            {
            // InternalLea.g:1276:1: ( '.' )
            // InternalLea.g:1277:2: '.'
            {
             before(grammarAccess.getOperationAccess().getFullStopKeyword_0_1()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getOperationAccess().getFullStopKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_0__1__Impl"


    // $ANTLR start "rule__Operation__Group_2__0"
    // InternalLea.g:1287:1: rule__Operation__Group_2__0 : rule__Operation__Group_2__0__Impl rule__Operation__Group_2__1 ;
    public final void rule__Operation__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1291:1: ( rule__Operation__Group_2__0__Impl rule__Operation__Group_2__1 )
            // InternalLea.g:1292:2: rule__Operation__Group_2__0__Impl rule__Operation__Group_2__1
            {
            pushFollow(FOLLOW_5);
            rule__Operation__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Operation__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_2__0"


    // $ANTLR start "rule__Operation__Group_2__0__Impl"
    // InternalLea.g:1299:1: rule__Operation__Group_2__0__Impl : ( '.' ) ;
    public final void rule__Operation__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1303:1: ( ( '.' ) )
            // InternalLea.g:1304:1: ( '.' )
            {
            // InternalLea.g:1304:1: ( '.' )
            // InternalLea.g:1305:2: '.'
            {
             before(grammarAccess.getOperationAccess().getFullStopKeyword_2_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getOperationAccess().getFullStopKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_2__0__Impl"


    // $ANTLR start "rule__Operation__Group_2__1"
    // InternalLea.g:1314:1: rule__Operation__Group_2__1 : rule__Operation__Group_2__1__Impl ;
    public final void rule__Operation__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1318:1: ( rule__Operation__Group_2__1__Impl )
            // InternalLea.g:1319:2: rule__Operation__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Operation__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_2__1"


    // $ANTLR start "rule__Operation__Group_2__1__Impl"
    // InternalLea.g:1325:1: rule__Operation__Group_2__1__Impl : ( ( rule__Operation__CallAssignment_2_1 ) ) ;
    public final void rule__Operation__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1329:1: ( ( ( rule__Operation__CallAssignment_2_1 ) ) )
            // InternalLea.g:1330:1: ( ( rule__Operation__CallAssignment_2_1 ) )
            {
            // InternalLea.g:1330:1: ( ( rule__Operation__CallAssignment_2_1 ) )
            // InternalLea.g:1331:2: ( rule__Operation__CallAssignment_2_1 )
            {
             before(grammarAccess.getOperationAccess().getCallAssignment_2_1()); 
            // InternalLea.g:1332:2: ( rule__Operation__CallAssignment_2_1 )
            // InternalLea.g:1332:3: rule__Operation__CallAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Operation__CallAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getOperationAccess().getCallAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Group_2__1__Impl"


    // $ANTLR start "rule__Call__Group__0"
    // InternalLea.g:1341:1: rule__Call__Group__0 : rule__Call__Group__0__Impl rule__Call__Group__1 ;
    public final void rule__Call__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1345:1: ( rule__Call__Group__0__Impl rule__Call__Group__1 )
            // InternalLea.g:1346:2: rule__Call__Group__0__Impl rule__Call__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__Call__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__0"


    // $ANTLR start "rule__Call__Group__0__Impl"
    // InternalLea.g:1353:1: rule__Call__Group__0__Impl : ( ( rule__Call__NameAssignment_0 ) ) ;
    public final void rule__Call__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1357:1: ( ( ( rule__Call__NameAssignment_0 ) ) )
            // InternalLea.g:1358:1: ( ( rule__Call__NameAssignment_0 ) )
            {
            // InternalLea.g:1358:1: ( ( rule__Call__NameAssignment_0 ) )
            // InternalLea.g:1359:2: ( rule__Call__NameAssignment_0 )
            {
             before(grammarAccess.getCallAccess().getNameAssignment_0()); 
            // InternalLea.g:1360:2: ( rule__Call__NameAssignment_0 )
            // InternalLea.g:1360:3: rule__Call__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Call__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getCallAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__0__Impl"


    // $ANTLR start "rule__Call__Group__1"
    // InternalLea.g:1368:1: rule__Call__Group__1 : rule__Call__Group__1__Impl rule__Call__Group__2 ;
    public final void rule__Call__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1372:1: ( rule__Call__Group__1__Impl rule__Call__Group__2 )
            // InternalLea.g:1373:2: rule__Call__Group__1__Impl rule__Call__Group__2
            {
            pushFollow(FOLLOW_18);
            rule__Call__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__1"


    // $ANTLR start "rule__Call__Group__1__Impl"
    // InternalLea.g:1380:1: rule__Call__Group__1__Impl : ( '(' ) ;
    public final void rule__Call__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1384:1: ( ( '(' ) )
            // InternalLea.g:1385:1: ( '(' )
            {
            // InternalLea.g:1385:1: ( '(' )
            // InternalLea.g:1386:2: '('
            {
             before(grammarAccess.getCallAccess().getLeftParenthesisKeyword_1()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getCallAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__1__Impl"


    // $ANTLR start "rule__Call__Group__2"
    // InternalLea.g:1395:1: rule__Call__Group__2 : rule__Call__Group__2__Impl rule__Call__Group__3 ;
    public final void rule__Call__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1399:1: ( rule__Call__Group__2__Impl rule__Call__Group__3 )
            // InternalLea.g:1400:2: rule__Call__Group__2__Impl rule__Call__Group__3
            {
            pushFollow(FOLLOW_18);
            rule__Call__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__2"


    // $ANTLR start "rule__Call__Group__2__Impl"
    // InternalLea.g:1407:1: rule__Call__Group__2__Impl : ( ( rule__Call__ParametersAssignment_2 )? ) ;
    public final void rule__Call__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1411:1: ( ( ( rule__Call__ParametersAssignment_2 )? ) )
            // InternalLea.g:1412:1: ( ( rule__Call__ParametersAssignment_2 )? )
            {
            // InternalLea.g:1412:1: ( ( rule__Call__ParametersAssignment_2 )? )
            // InternalLea.g:1413:2: ( rule__Call__ParametersAssignment_2 )?
            {
             before(grammarAccess.getCallAccess().getParametersAssignment_2()); 
            // InternalLea.g:1414:2: ( rule__Call__ParametersAssignment_2 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=RULE_ID && LA14_0<=RULE_STRING)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalLea.g:1414:3: rule__Call__ParametersAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Call__ParametersAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCallAccess().getParametersAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__2__Impl"


    // $ANTLR start "rule__Call__Group__3"
    // InternalLea.g:1422:1: rule__Call__Group__3 : rule__Call__Group__3__Impl ;
    public final void rule__Call__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1426:1: ( rule__Call__Group__3__Impl )
            // InternalLea.g:1427:2: rule__Call__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Call__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__3"


    // $ANTLR start "rule__Call__Group__3__Impl"
    // InternalLea.g:1433:1: rule__Call__Group__3__Impl : ( ')' ) ;
    public final void rule__Call__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1437:1: ( ( ')' ) )
            // InternalLea.g:1438:1: ( ')' )
            {
            // InternalLea.g:1438:1: ( ')' )
            // InternalLea.g:1439:2: ')'
            {
             before(grammarAccess.getCallAccess().getRightParenthesisKeyword_3()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getCallAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__3__Impl"


    // $ANTLR start "rule__ParameterList__Group__0"
    // InternalLea.g:1449:1: rule__ParameterList__Group__0 : rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 ;
    public final void rule__ParameterList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1453:1: ( rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 )
            // InternalLea.g:1454:2: rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__ParameterList__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ParameterList__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__0"


    // $ANTLR start "rule__ParameterList__Group__0__Impl"
    // InternalLea.g:1461:1: rule__ParameterList__Group__0__Impl : ( ( rule__ParameterList__ParameterListAssignment_0 ) ) ;
    public final void rule__ParameterList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1465:1: ( ( ( rule__ParameterList__ParameterListAssignment_0 ) ) )
            // InternalLea.g:1466:1: ( ( rule__ParameterList__ParameterListAssignment_0 ) )
            {
            // InternalLea.g:1466:1: ( ( rule__ParameterList__ParameterListAssignment_0 ) )
            // InternalLea.g:1467:2: ( rule__ParameterList__ParameterListAssignment_0 )
            {
             before(grammarAccess.getParameterListAccess().getParameterListAssignment_0()); 
            // InternalLea.g:1468:2: ( rule__ParameterList__ParameterListAssignment_0 )
            // InternalLea.g:1468:3: rule__ParameterList__ParameterListAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__ParameterListAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getParameterListAccess().getParameterListAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__0__Impl"


    // $ANTLR start "rule__ParameterList__Group__1"
    // InternalLea.g:1476:1: rule__ParameterList__Group__1 : rule__ParameterList__Group__1__Impl ;
    public final void rule__ParameterList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1480:1: ( rule__ParameterList__Group__1__Impl )
            // InternalLea.g:1481:2: rule__ParameterList__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__1"


    // $ANTLR start "rule__ParameterList__Group__1__Impl"
    // InternalLea.g:1487:1: rule__ParameterList__Group__1__Impl : ( ( rule__ParameterList__Group_1__0 )* ) ;
    public final void rule__ParameterList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1491:1: ( ( ( rule__ParameterList__Group_1__0 )* ) )
            // InternalLea.g:1492:1: ( ( rule__ParameterList__Group_1__0 )* )
            {
            // InternalLea.g:1492:1: ( ( rule__ParameterList__Group_1__0 )* )
            // InternalLea.g:1493:2: ( rule__ParameterList__Group_1__0 )*
            {
             before(grammarAccess.getParameterListAccess().getGroup_1()); 
            // InternalLea.g:1494:2: ( rule__ParameterList__Group_1__0 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==17) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalLea.g:1494:3: rule__ParameterList__Group_1__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__ParameterList__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getParameterListAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__1__Impl"


    // $ANTLR start "rule__ParameterList__Group_1__0"
    // InternalLea.g:1503:1: rule__ParameterList__Group_1__0 : rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 ;
    public final void rule__ParameterList__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1507:1: ( rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 )
            // InternalLea.g:1508:2: rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1
            {
            pushFollow(FOLLOW_20);
            rule__ParameterList__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ParameterList__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__0"


    // $ANTLR start "rule__ParameterList__Group_1__0__Impl"
    // InternalLea.g:1515:1: rule__ParameterList__Group_1__0__Impl : ( ',' ) ;
    public final void rule__ParameterList__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1519:1: ( ( ',' ) )
            // InternalLea.g:1520:1: ( ',' )
            {
            // InternalLea.g:1520:1: ( ',' )
            // InternalLea.g:1521:2: ','
            {
             before(grammarAccess.getParameterListAccess().getCommaKeyword_1_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getParameterListAccess().getCommaKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__0__Impl"


    // $ANTLR start "rule__ParameterList__Group_1__1"
    // InternalLea.g:1530:1: rule__ParameterList__Group_1__1 : rule__ParameterList__Group_1__1__Impl ;
    public final void rule__ParameterList__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1534:1: ( rule__ParameterList__Group_1__1__Impl )
            // InternalLea.g:1535:2: rule__ParameterList__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__1"


    // $ANTLR start "rule__ParameterList__Group_1__1__Impl"
    // InternalLea.g:1541:1: rule__ParameterList__Group_1__1__Impl : ( ( rule__ParameterList__ParameterListAssignment_1_1 ) ) ;
    public final void rule__ParameterList__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1545:1: ( ( ( rule__ParameterList__ParameterListAssignment_1_1 ) ) )
            // InternalLea.g:1546:1: ( ( rule__ParameterList__ParameterListAssignment_1_1 ) )
            {
            // InternalLea.g:1546:1: ( ( rule__ParameterList__ParameterListAssignment_1_1 ) )
            // InternalLea.g:1547:2: ( rule__ParameterList__ParameterListAssignment_1_1 )
            {
             before(grammarAccess.getParameterListAccess().getParameterListAssignment_1_1()); 
            // InternalLea.g:1548:2: ( rule__ParameterList__ParameterListAssignment_1_1 )
            // InternalLea.g:1548:3: rule__ParameterList__ParameterListAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__ParameterListAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getParameterListAccess().getParameterListAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__1__Impl"


    // $ANTLR start "rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0"
    // InternalLea.g:1557:1: rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0 : ( ruleElementDeclaration ) ;
    public final void rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1561:1: ( ( ruleElementDeclaration ) )
            // InternalLea.g:1562:2: ( ruleElementDeclaration )
            {
            // InternalLea.g:1562:2: ( ruleElementDeclaration )
            // InternalLea.g:1563:3: ruleElementDeclaration
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getElementDeclarationsElementDeclarationParserRuleCall_1_0_0()); 
            pushFollow(FOLLOW_2);
            ruleElementDeclaration();

            state._fsp--;

             after(grammarAccess.getAnalysisDefinitionAccess().getElementDeclarationsElementDeclarationParserRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnalysisDefinition__ElementDeclarationsAssignment_1_0"


    // $ANTLR start "rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1"
    // InternalLea.g:1572:1: rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1 : ( ruleChangeIdentifierAssignment ) ;
    public final void rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1576:1: ( ( ruleChangeIdentifierAssignment ) )
            // InternalLea.g:1577:2: ( ruleChangeIdentifierAssignment )
            {
            // InternalLea.g:1577:2: ( ruleChangeIdentifierAssignment )
            // InternalLea.g:1578:3: ruleChangeIdentifierAssignment
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getChangeIdentifierAssignmentsChangeIdentifierAssignmentParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleChangeIdentifierAssignment();

            state._fsp--;

             after(grammarAccess.getAnalysisDefinitionAccess().getChangeIdentifierAssignmentsChangeIdentifierAssignmentParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnalysisDefinition__ChangeIdentifierAssignmentsAssignment_1_1"


    // $ANTLR start "rule__ChangeIdentifierAssignment__IdentifierAssignment_1"
    // InternalLea.g:1587:1: rule__ChangeIdentifierAssignment__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ChangeIdentifierAssignment__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1591:1: ( ( RULE_ID ) )
            // InternalLea.g:1592:2: ( RULE_ID )
            {
            // InternalLea.g:1592:2: ( RULE_ID )
            // InternalLea.g:1593:3: RULE_ID
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getChangeIdentifierAssignmentAccess().getIdentifierIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__IdentifierAssignment_1"


    // $ANTLR start "rule__ChangeIdentifierAssignment__ElementsAssignment_3"
    // InternalLea.g:1602:1: rule__ChangeIdentifierAssignment__ElementsAssignment_3 : ( RULE_ID ) ;
    public final void rule__ChangeIdentifierAssignment__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1606:1: ( ( RULE_ID ) )
            // InternalLea.g:1607:2: ( RULE_ID )
            {
            // InternalLea.g:1607:2: ( RULE_ID )
            // InternalLea.g:1608:3: RULE_ID
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__ElementsAssignment_3"


    // $ANTLR start "rule__ChangeIdentifierAssignment__ElementsAssignment_4_1"
    // InternalLea.g:1617:1: rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 : ( RULE_ID ) ;
    public final void rule__ChangeIdentifierAssignment__ElementsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1621:1: ( ( RULE_ID ) )
            // InternalLea.g:1622:2: ( RULE_ID )
            {
            // InternalLea.g:1622:2: ( RULE_ID )
            // InternalLea.g:1623:3: RULE_ID
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsIDTerminalRuleCall_4_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsIDTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ChangeIdentifierAssignment__ElementsAssignment_4_1"


    // $ANTLR start "rule__ElementDeclaration__GenericTypAssignment_0"
    // InternalLea.g:1632:1: rule__ElementDeclaration__GenericTypAssignment_0 : ( ruleGenericType ) ;
    public final void rule__ElementDeclaration__GenericTypAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1636:1: ( ( ruleGenericType ) )
            // InternalLea.g:1637:2: ( ruleGenericType )
            {
            // InternalLea.g:1637:2: ( ruleGenericType )
            // InternalLea.g:1638:3: ruleGenericType
            {
             before(grammarAccess.getElementDeclarationAccess().getGenericTypGenericTypeParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleGenericType();

            state._fsp--;

             after(grammarAccess.getElementDeclarationAccess().getGenericTypGenericTypeParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__GenericTypAssignment_0"


    // $ANTLR start "rule__ElementDeclaration__ParameterTypeAssignment_2"
    // InternalLea.g:1647:1: rule__ElementDeclaration__ParameterTypeAssignment_2 : ( RULE_ID ) ;
    public final void rule__ElementDeclaration__ParameterTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1651:1: ( ( RULE_ID ) )
            // InternalLea.g:1652:2: ( RULE_ID )
            {
            // InternalLea.g:1652:2: ( RULE_ID )
            // InternalLea.g:1653:3: RULE_ID
            {
             before(grammarAccess.getElementDeclarationAccess().getParameterTypeIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElementDeclarationAccess().getParameterTypeIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__ParameterTypeAssignment_2"


    // $ANTLR start "rule__ElementDeclaration__SetAssignment_4"
    // InternalLea.g:1662:1: rule__ElementDeclaration__SetAssignment_4 : ( ruleSetDefinition ) ;
    public final void rule__ElementDeclaration__SetAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1666:1: ( ( ruleSetDefinition ) )
            // InternalLea.g:1667:2: ( ruleSetDefinition )
            {
            // InternalLea.g:1667:2: ( ruleSetDefinition )
            // InternalLea.g:1668:3: ruleSetDefinition
            {
             before(grammarAccess.getElementDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleSetDefinition();

            state._fsp--;

             after(grammarAccess.getElementDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__SetAssignment_4"


    // $ANTLR start "rule__ElementDeclaration__NameAssignment_5"
    // InternalLea.g:1677:1: rule__ElementDeclaration__NameAssignment_5 : ( RULE_ID ) ;
    public final void rule__ElementDeclaration__NameAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1681:1: ( ( RULE_ID ) )
            // InternalLea.g:1682:2: ( RULE_ID )
            {
            // InternalLea.g:1682:2: ( RULE_ID )
            // InternalLea.g:1683:3: RULE_ID
            {
             before(grammarAccess.getElementDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElementDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__NameAssignment_5"


    // $ANTLR start "rule__ElementDeclaration__InitializationAssignment_6"
    // InternalLea.g:1692:1: rule__ElementDeclaration__InitializationAssignment_6 : ( ruleAssignment ) ;
    public final void rule__ElementDeclaration__InitializationAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1696:1: ( ( ruleAssignment ) )
            // InternalLea.g:1697:2: ( ruleAssignment )
            {
            // InternalLea.g:1697:2: ( ruleAssignment )
            // InternalLea.g:1698:3: ruleAssignment
            {
             before(grammarAccess.getElementDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getElementDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElementDeclaration__InitializationAssignment_6"


    // $ANTLR start "rule__SetDefinition__IterationAssignment_2"
    // InternalLea.g:1707:1: rule__SetDefinition__IterationAssignment_2 : ( ruleIteration ) ;
    public final void rule__SetDefinition__IterationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1711:1: ( ( ruleIteration ) )
            // InternalLea.g:1712:2: ( ruleIteration )
            {
            // InternalLea.g:1712:2: ( ruleIteration )
            // InternalLea.g:1713:3: ruleIteration
            {
             before(grammarAccess.getSetDefinitionAccess().getIterationIterationParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleIteration();

            state._fsp--;

             after(grammarAccess.getSetDefinitionAccess().getIterationIterationParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetDefinition__IterationAssignment_2"


    // $ANTLR start "rule__Iteration__IteratorAssignment_0"
    // InternalLea.g:1722:1: rule__Iteration__IteratorAssignment_0 : ( RULE_ID ) ;
    public final void rule__Iteration__IteratorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1726:1: ( ( RULE_ID ) )
            // InternalLea.g:1727:2: ( RULE_ID )
            {
            // InternalLea.g:1727:2: ( RULE_ID )
            // InternalLea.g:1728:3: RULE_ID
            {
             before(grammarAccess.getIterationAccess().getIteratorIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getIterationAccess().getIteratorIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__IteratorAssignment_0"


    // $ANTLR start "rule__Iteration__IterableAssignment_2"
    // InternalLea.g:1737:1: rule__Iteration__IterableAssignment_2 : ( RULE_ID ) ;
    public final void rule__Iteration__IterableAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1741:1: ( ( RULE_ID ) )
            // InternalLea.g:1742:2: ( RULE_ID )
            {
            // InternalLea.g:1742:2: ( RULE_ID )
            // InternalLea.g:1743:3: RULE_ID
            {
             before(grammarAccess.getIterationAccess().getIterableIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getIterationAccess().getIterableIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Iteration__IterableAssignment_2"


    // $ANTLR start "rule__Assignment__ElementAssignment_1_0"
    // InternalLea.g:1752:1: rule__Assignment__ElementAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__Assignment__ElementAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1756:1: ( ( RULE_ID ) )
            // InternalLea.g:1757:2: ( RULE_ID )
            {
            // InternalLea.g:1757:2: ( RULE_ID )
            // InternalLea.g:1758:3: RULE_ID
            {
             before(grammarAccess.getAssignmentAccess().getElementIDTerminalRuleCall_1_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getElementIDTerminalRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__ElementAssignment_1_0"


    // $ANTLR start "rule__Assignment__OperationAssignment_1_1"
    // InternalLea.g:1767:1: rule__Assignment__OperationAssignment_1_1 : ( ruleOperation ) ;
    public final void rule__Assignment__OperationAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1771:1: ( ( ruleOperation ) )
            // InternalLea.g:1772:2: ( ruleOperation )
            {
            // InternalLea.g:1772:2: ( ruleOperation )
            // InternalLea.g:1773:3: ruleOperation
            {
             before(grammarAccess.getAssignmentAccess().getOperationOperationParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleOperation();

            state._fsp--;

             after(grammarAccess.getAssignmentAccess().getOperationOperationParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__OperationAssignment_1_1"


    // $ANTLR start "rule__Operation__ElementAssignment_0_0"
    // InternalLea.g:1782:1: rule__Operation__ElementAssignment_0_0 : ( RULE_ID ) ;
    public final void rule__Operation__ElementAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1786:1: ( ( RULE_ID ) )
            // InternalLea.g:1787:2: ( RULE_ID )
            {
            // InternalLea.g:1787:2: ( RULE_ID )
            // InternalLea.g:1788:3: RULE_ID
            {
             before(grammarAccess.getOperationAccess().getElementIDTerminalRuleCall_0_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getOperationAccess().getElementIDTerminalRuleCall_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__ElementAssignment_0_0"


    // $ANTLR start "rule__Operation__CallAssignment_1"
    // InternalLea.g:1797:1: rule__Operation__CallAssignment_1 : ( ruleCall ) ;
    public final void rule__Operation__CallAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1801:1: ( ( ruleCall ) )
            // InternalLea.g:1802:2: ( ruleCall )
            {
            // InternalLea.g:1802:2: ( ruleCall )
            // InternalLea.g:1803:3: ruleCall
            {
             before(grammarAccess.getOperationAccess().getCallCallParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCall();

            state._fsp--;

             after(grammarAccess.getOperationAccess().getCallCallParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__CallAssignment_1"


    // $ANTLR start "rule__Operation__CallAssignment_2_1"
    // InternalLea.g:1812:1: rule__Operation__CallAssignment_2_1 : ( ruleCall ) ;
    public final void rule__Operation__CallAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1816:1: ( ( ruleCall ) )
            // InternalLea.g:1817:2: ( ruleCall )
            {
            // InternalLea.g:1817:2: ( ruleCall )
            // InternalLea.g:1818:3: ruleCall
            {
             before(grammarAccess.getOperationAccess().getCallCallParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCall();

            state._fsp--;

             after(grammarAccess.getOperationAccess().getCallCallParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__CallAssignment_2_1"


    // $ANTLR start "rule__Call__NameAssignment_0"
    // InternalLea.g:1827:1: rule__Call__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Call__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1831:1: ( ( RULE_ID ) )
            // InternalLea.g:1832:2: ( RULE_ID )
            {
            // InternalLea.g:1832:2: ( RULE_ID )
            // InternalLea.g:1833:3: RULE_ID
            {
             before(grammarAccess.getCallAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getCallAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__NameAssignment_0"


    // $ANTLR start "rule__Call__ParametersAssignment_2"
    // InternalLea.g:1842:1: rule__Call__ParametersAssignment_2 : ( ruleParameterList ) ;
    public final void rule__Call__ParametersAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1846:1: ( ( ruleParameterList ) )
            // InternalLea.g:1847:2: ( ruleParameterList )
            {
            // InternalLea.g:1847:2: ( ruleParameterList )
            // InternalLea.g:1848:3: ruleParameterList
            {
             before(grammarAccess.getCallAccess().getParametersParameterListParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleParameterList();

            state._fsp--;

             after(grammarAccess.getCallAccess().getParametersParameterListParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__ParametersAssignment_2"


    // $ANTLR start "rule__ParameterList__ParameterListAssignment_0"
    // InternalLea.g:1857:1: rule__ParameterList__ParameterListAssignment_0 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParameterListAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1861:1: ( ( ruleParameter ) )
            // InternalLea.g:1862:2: ( ruleParameter )
            {
            // InternalLea.g:1862:2: ( ruleParameter )
            // InternalLea.g:1863:3: ruleParameter
            {
             before(grammarAccess.getParameterListAccess().getParameterListParameterParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getParameterListAccess().getParameterListParameterParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__ParameterListAssignment_0"


    // $ANTLR start "rule__ParameterList__ParameterListAssignment_1_1"
    // InternalLea.g:1872:1: rule__ParameterList__ParameterListAssignment_1_1 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParameterListAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1876:1: ( ( ruleParameter ) )
            // InternalLea.g:1877:2: ( ruleParameter )
            {
            // InternalLea.g:1877:2: ( ruleParameter )
            // InternalLea.g:1878:3: ruleParameter
            {
             before(grammarAccess.getParameterListAccess().getParameterListParameterParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getParameterListAccess().getParameterListParameterParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__ParameterListAssignment_1_1"


    // $ANTLR start "rule__Parameter__TextAssignment_0"
    // InternalLea.g:1887:1: rule__Parameter__TextAssignment_0 : ( RULE_STRING ) ;
    public final void rule__Parameter__TextAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1891:1: ( ( RULE_STRING ) )
            // InternalLea.g:1892:2: ( RULE_STRING )
            {
            // InternalLea.g:1892:2: ( RULE_STRING )
            // InternalLea.g:1893:3: RULE_STRING
            {
             before(grammarAccess.getParameterAccess().getTextSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getParameterAccess().getTextSTRINGTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__TextAssignment_0"


    // $ANTLR start "rule__Parameter__ElementAssignment_1"
    // InternalLea.g:1902:1: rule__Parameter__ElementAssignment_1 : ( RULE_ID ) ;
    public final void rule__Parameter__ElementAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1906:1: ( ( RULE_ID ) )
            // InternalLea.g:1907:2: ( RULE_ID )
            {
            // InternalLea.g:1907:2: ( RULE_ID )
            // InternalLea.g:1908:3: RULE_ID
            {
             before(grammarAccess.getParameterAccess().getElementIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getParameterAccess().getElementIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__ElementAssignment_1"


    // $ANTLR start "rule__Parameter__OperationAssignment_2"
    // InternalLea.g:1917:1: rule__Parameter__OperationAssignment_2 : ( ruleOperation ) ;
    public final void rule__Parameter__OperationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1921:1: ( ( ruleOperation ) )
            // InternalLea.g:1922:2: ( ruleOperation )
            {
            // InternalLea.g:1922:2: ( ruleOperation )
            // InternalLea.g:1923:3: ruleOperation
            {
             before(grammarAccess.getParameterAccess().getOperationOperationParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleOperation();

            state._fsp--;

             after(grammarAccess.getParameterAccess().getOperationOperationParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__OperationAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000007800L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000007802L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000100010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000810000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000004000030L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000000030L});

}