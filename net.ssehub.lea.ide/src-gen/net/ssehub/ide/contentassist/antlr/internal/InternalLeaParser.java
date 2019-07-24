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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'assign'", "'to'", "';'", "','", "'Artifact'", "'<'", "'>'", "'Fragment'", "'Result'", "'['", "']'", "':'", "'='", "'.'", "'('", "')'"
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


    // $ANTLR start "entryRuleArtifactDeclaration"
    // InternalLea.g:103:1: entryRuleArtifactDeclaration : ruleArtifactDeclaration EOF ;
    public final void entryRuleArtifactDeclaration() throws RecognitionException {
        try {
            // InternalLea.g:104:1: ( ruleArtifactDeclaration EOF )
            // InternalLea.g:105:1: ruleArtifactDeclaration EOF
            {
             before(grammarAccess.getArtifactDeclarationRule()); 
            pushFollow(FOLLOW_1);
            ruleArtifactDeclaration();

            state._fsp--;

             after(grammarAccess.getArtifactDeclarationRule()); 
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
    // $ANTLR end "entryRuleArtifactDeclaration"


    // $ANTLR start "ruleArtifactDeclaration"
    // InternalLea.g:112:1: ruleArtifactDeclaration : ( ( rule__ArtifactDeclaration__Group__0 ) ) ;
    public final void ruleArtifactDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:116:2: ( ( ( rule__ArtifactDeclaration__Group__0 ) ) )
            // InternalLea.g:117:2: ( ( rule__ArtifactDeclaration__Group__0 ) )
            {
            // InternalLea.g:117:2: ( ( rule__ArtifactDeclaration__Group__0 ) )
            // InternalLea.g:118:3: ( rule__ArtifactDeclaration__Group__0 )
            {
             before(grammarAccess.getArtifactDeclarationAccess().getGroup()); 
            // InternalLea.g:119:3: ( rule__ArtifactDeclaration__Group__0 )
            // InternalLea.g:119:4: rule__ArtifactDeclaration__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getArtifactDeclarationAccess().getGroup()); 

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
    // $ANTLR end "ruleArtifactDeclaration"


    // $ANTLR start "entryRuleFragmentDeclaration"
    // InternalLea.g:128:1: entryRuleFragmentDeclaration : ruleFragmentDeclaration EOF ;
    public final void entryRuleFragmentDeclaration() throws RecognitionException {
        try {
            // InternalLea.g:129:1: ( ruleFragmentDeclaration EOF )
            // InternalLea.g:130:1: ruleFragmentDeclaration EOF
            {
             before(grammarAccess.getFragmentDeclarationRule()); 
            pushFollow(FOLLOW_1);
            ruleFragmentDeclaration();

            state._fsp--;

             after(grammarAccess.getFragmentDeclarationRule()); 
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
    // $ANTLR end "entryRuleFragmentDeclaration"


    // $ANTLR start "ruleFragmentDeclaration"
    // InternalLea.g:137:1: ruleFragmentDeclaration : ( ( rule__FragmentDeclaration__Group__0 ) ) ;
    public final void ruleFragmentDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:141:2: ( ( ( rule__FragmentDeclaration__Group__0 ) ) )
            // InternalLea.g:142:2: ( ( rule__FragmentDeclaration__Group__0 ) )
            {
            // InternalLea.g:142:2: ( ( rule__FragmentDeclaration__Group__0 ) )
            // InternalLea.g:143:3: ( rule__FragmentDeclaration__Group__0 )
            {
             before(grammarAccess.getFragmentDeclarationAccess().getGroup()); 
            // InternalLea.g:144:3: ( rule__FragmentDeclaration__Group__0 )
            // InternalLea.g:144:4: rule__FragmentDeclaration__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFragmentDeclarationAccess().getGroup()); 

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
    // $ANTLR end "ruleFragmentDeclaration"


    // $ANTLR start "entryRuleResultDeclaration"
    // InternalLea.g:153:1: entryRuleResultDeclaration : ruleResultDeclaration EOF ;
    public final void entryRuleResultDeclaration() throws RecognitionException {
        try {
            // InternalLea.g:154:1: ( ruleResultDeclaration EOF )
            // InternalLea.g:155:1: ruleResultDeclaration EOF
            {
             before(grammarAccess.getResultDeclarationRule()); 
            pushFollow(FOLLOW_1);
            ruleResultDeclaration();

            state._fsp--;

             after(grammarAccess.getResultDeclarationRule()); 
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
    // $ANTLR end "entryRuleResultDeclaration"


    // $ANTLR start "ruleResultDeclaration"
    // InternalLea.g:162:1: ruleResultDeclaration : ( ( rule__ResultDeclaration__Group__0 ) ) ;
    public final void ruleResultDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:166:2: ( ( ( rule__ResultDeclaration__Group__0 ) ) )
            // InternalLea.g:167:2: ( ( rule__ResultDeclaration__Group__0 ) )
            {
            // InternalLea.g:167:2: ( ( rule__ResultDeclaration__Group__0 ) )
            // InternalLea.g:168:3: ( rule__ResultDeclaration__Group__0 )
            {
             before(grammarAccess.getResultDeclarationAccess().getGroup()); 
            // InternalLea.g:169:3: ( rule__ResultDeclaration__Group__0 )
            // InternalLea.g:169:4: rule__ResultDeclaration__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResultDeclarationAccess().getGroup()); 

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
    // $ANTLR end "ruleResultDeclaration"


    // $ANTLR start "entryRuleSetDefinition"
    // InternalLea.g:178:1: entryRuleSetDefinition : ruleSetDefinition EOF ;
    public final void entryRuleSetDefinition() throws RecognitionException {
        try {
            // InternalLea.g:179:1: ( ruleSetDefinition EOF )
            // InternalLea.g:180:1: ruleSetDefinition EOF
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
    // InternalLea.g:187:1: ruleSetDefinition : ( ( rule__SetDefinition__Group__0 ) ) ;
    public final void ruleSetDefinition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:191:2: ( ( ( rule__SetDefinition__Group__0 ) ) )
            // InternalLea.g:192:2: ( ( rule__SetDefinition__Group__0 ) )
            {
            // InternalLea.g:192:2: ( ( rule__SetDefinition__Group__0 ) )
            // InternalLea.g:193:3: ( rule__SetDefinition__Group__0 )
            {
             before(grammarAccess.getSetDefinitionAccess().getGroup()); 
            // InternalLea.g:194:3: ( rule__SetDefinition__Group__0 )
            // InternalLea.g:194:4: rule__SetDefinition__Group__0
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
    // InternalLea.g:203:1: entryRuleIteration : ruleIteration EOF ;
    public final void entryRuleIteration() throws RecognitionException {
        try {
            // InternalLea.g:204:1: ( ruleIteration EOF )
            // InternalLea.g:205:1: ruleIteration EOF
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
    // InternalLea.g:212:1: ruleIteration : ( ( rule__Iteration__Group__0 ) ) ;
    public final void ruleIteration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:216:2: ( ( ( rule__Iteration__Group__0 ) ) )
            // InternalLea.g:217:2: ( ( rule__Iteration__Group__0 ) )
            {
            // InternalLea.g:217:2: ( ( rule__Iteration__Group__0 ) )
            // InternalLea.g:218:3: ( rule__Iteration__Group__0 )
            {
             before(grammarAccess.getIterationAccess().getGroup()); 
            // InternalLea.g:219:3: ( rule__Iteration__Group__0 )
            // InternalLea.g:219:4: rule__Iteration__Group__0
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
    // InternalLea.g:228:1: entryRuleAssignment : ruleAssignment EOF ;
    public final void entryRuleAssignment() throws RecognitionException {
        try {
            // InternalLea.g:229:1: ( ruleAssignment EOF )
            // InternalLea.g:230:1: ruleAssignment EOF
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
    // InternalLea.g:237:1: ruleAssignment : ( ( rule__Assignment__Group__0 ) ) ;
    public final void ruleAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:241:2: ( ( ( rule__Assignment__Group__0 ) ) )
            // InternalLea.g:242:2: ( ( rule__Assignment__Group__0 ) )
            {
            // InternalLea.g:242:2: ( ( rule__Assignment__Group__0 ) )
            // InternalLea.g:243:3: ( rule__Assignment__Group__0 )
            {
             before(grammarAccess.getAssignmentAccess().getGroup()); 
            // InternalLea.g:244:3: ( rule__Assignment__Group__0 )
            // InternalLea.g:244:4: rule__Assignment__Group__0
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
    // InternalLea.g:253:1: entryRuleOperation : ruleOperation EOF ;
    public final void entryRuleOperation() throws RecognitionException {
        try {
            // InternalLea.g:254:1: ( ruleOperation EOF )
            // InternalLea.g:255:1: ruleOperation EOF
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
    // InternalLea.g:262:1: ruleOperation : ( ( rule__Operation__Group__0 ) ) ;
    public final void ruleOperation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:266:2: ( ( ( rule__Operation__Group__0 ) ) )
            // InternalLea.g:267:2: ( ( rule__Operation__Group__0 ) )
            {
            // InternalLea.g:267:2: ( ( rule__Operation__Group__0 ) )
            // InternalLea.g:268:3: ( rule__Operation__Group__0 )
            {
             before(grammarAccess.getOperationAccess().getGroup()); 
            // InternalLea.g:269:3: ( rule__Operation__Group__0 )
            // InternalLea.g:269:4: rule__Operation__Group__0
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
    // InternalLea.g:278:1: entryRuleCall : ruleCall EOF ;
    public final void entryRuleCall() throws RecognitionException {
        try {
            // InternalLea.g:279:1: ( ruleCall EOF )
            // InternalLea.g:280:1: ruleCall EOF
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
    // InternalLea.g:287:1: ruleCall : ( ( rule__Call__Group__0 ) ) ;
    public final void ruleCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:291:2: ( ( ( rule__Call__Group__0 ) ) )
            // InternalLea.g:292:2: ( ( rule__Call__Group__0 ) )
            {
            // InternalLea.g:292:2: ( ( rule__Call__Group__0 ) )
            // InternalLea.g:293:3: ( rule__Call__Group__0 )
            {
             before(grammarAccess.getCallAccess().getGroup()); 
            // InternalLea.g:294:3: ( rule__Call__Group__0 )
            // InternalLea.g:294:4: rule__Call__Group__0
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
    // InternalLea.g:303:1: entryRuleParameterList : ruleParameterList EOF ;
    public final void entryRuleParameterList() throws RecognitionException {
        try {
            // InternalLea.g:304:1: ( ruleParameterList EOF )
            // InternalLea.g:305:1: ruleParameterList EOF
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
    // InternalLea.g:312:1: ruleParameterList : ( ( rule__ParameterList__Group__0 ) ) ;
    public final void ruleParameterList() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:316:2: ( ( ( rule__ParameterList__Group__0 ) ) )
            // InternalLea.g:317:2: ( ( rule__ParameterList__Group__0 ) )
            {
            // InternalLea.g:317:2: ( ( rule__ParameterList__Group__0 ) )
            // InternalLea.g:318:3: ( rule__ParameterList__Group__0 )
            {
             before(grammarAccess.getParameterListAccess().getGroup()); 
            // InternalLea.g:319:3: ( rule__ParameterList__Group__0 )
            // InternalLea.g:319:4: rule__ParameterList__Group__0
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
    // InternalLea.g:328:1: entryRuleParameter : ruleParameter EOF ;
    public final void entryRuleParameter() throws RecognitionException {
        try {
            // InternalLea.g:329:1: ( ruleParameter EOF )
            // InternalLea.g:330:1: ruleParameter EOF
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
    // InternalLea.g:337:1: ruleParameter : ( ( rule__Parameter__Alternatives ) ) ;
    public final void ruleParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:341:2: ( ( ( rule__Parameter__Alternatives ) ) )
            // InternalLea.g:342:2: ( ( rule__Parameter__Alternatives ) )
            {
            // InternalLea.g:342:2: ( ( rule__Parameter__Alternatives ) )
            // InternalLea.g:343:3: ( rule__Parameter__Alternatives )
            {
             before(grammarAccess.getParameterAccess().getAlternatives()); 
            // InternalLea.g:344:3: ( rule__Parameter__Alternatives )
            // InternalLea.g:344:4: rule__Parameter__Alternatives
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
    // InternalLea.g:352:1: rule__AnalysisDefinition__Alternatives_1 : ( ( ( rule__AnalysisDefinition__ElementsAssignment_1_0 ) ) | ( ( rule__AnalysisDefinition__ElementsAssignment_1_1 ) ) | ( ( rule__AnalysisDefinition__ElementsAssignment_1_2 ) ) | ( ( rule__AnalysisDefinition__ElementsAssignment_1_3 ) ) );
    public final void rule__AnalysisDefinition__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:356:1: ( ( ( rule__AnalysisDefinition__ElementsAssignment_1_0 ) ) | ( ( rule__AnalysisDefinition__ElementsAssignment_1_1 ) ) | ( ( rule__AnalysisDefinition__ElementsAssignment_1_2 ) ) | ( ( rule__AnalysisDefinition__ElementsAssignment_1_3 ) ) )
            int alt1=4;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt1=1;
                }
                break;
            case 18:
                {
                alt1=2;
                }
                break;
            case 19:
                {
                alt1=3;
                }
                break;
            case 11:
                {
                alt1=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalLea.g:357:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_0 ) )
                    {
                    // InternalLea.g:357:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_0 ) )
                    // InternalLea.g:358:3: ( rule__AnalysisDefinition__ElementsAssignment_1_0 )
                    {
                     before(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_0()); 
                    // InternalLea.g:359:3: ( rule__AnalysisDefinition__ElementsAssignment_1_0 )
                    // InternalLea.g:359:4: rule__AnalysisDefinition__ElementsAssignment_1_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__AnalysisDefinition__ElementsAssignment_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLea.g:363:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_1 ) )
                    {
                    // InternalLea.g:363:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_1 ) )
                    // InternalLea.g:364:3: ( rule__AnalysisDefinition__ElementsAssignment_1_1 )
                    {
                     before(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_1()); 
                    // InternalLea.g:365:3: ( rule__AnalysisDefinition__ElementsAssignment_1_1 )
                    // InternalLea.g:365:4: rule__AnalysisDefinition__ElementsAssignment_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__AnalysisDefinition__ElementsAssignment_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalLea.g:369:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_2 ) )
                    {
                    // InternalLea.g:369:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_2 ) )
                    // InternalLea.g:370:3: ( rule__AnalysisDefinition__ElementsAssignment_1_2 )
                    {
                     before(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_2()); 
                    // InternalLea.g:371:3: ( rule__AnalysisDefinition__ElementsAssignment_1_2 )
                    // InternalLea.g:371:4: rule__AnalysisDefinition__ElementsAssignment_1_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__AnalysisDefinition__ElementsAssignment_1_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalLea.g:375:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_3 ) )
                    {
                    // InternalLea.g:375:2: ( ( rule__AnalysisDefinition__ElementsAssignment_1_3 ) )
                    // InternalLea.g:376:3: ( rule__AnalysisDefinition__ElementsAssignment_1_3 )
                    {
                     before(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_3()); 
                    // InternalLea.g:377:3: ( rule__AnalysisDefinition__ElementsAssignment_1_3 )
                    // InternalLea.g:377:4: rule__AnalysisDefinition__ElementsAssignment_1_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__AnalysisDefinition__ElementsAssignment_1_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getAnalysisDefinitionAccess().getElementsAssignment_1_3()); 

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


    // $ANTLR start "rule__Assignment__Alternatives_1"
    // InternalLea.g:385:1: rule__Assignment__Alternatives_1 : ( ( ( rule__Assignment__ElementAssignment_1_0 ) ) | ( ( rule__Assignment__OperationAssignment_1_1 ) ) );
    public final void rule__Assignment__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:389:1: ( ( ( rule__Assignment__ElementAssignment_1_0 ) ) | ( ( rule__Assignment__OperationAssignment_1_1 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==EOF||LA2_1==11||LA2_1==13||LA2_1==15||(LA2_1>=18 && LA2_1<=19)) ) {
                    alt2=1;
                }
                else if ( ((LA2_1>=24 && LA2_1<=25)) ) {
                    alt2=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalLea.g:390:2: ( ( rule__Assignment__ElementAssignment_1_0 ) )
                    {
                    // InternalLea.g:390:2: ( ( rule__Assignment__ElementAssignment_1_0 ) )
                    // InternalLea.g:391:3: ( rule__Assignment__ElementAssignment_1_0 )
                    {
                     before(grammarAccess.getAssignmentAccess().getElementAssignment_1_0()); 
                    // InternalLea.g:392:3: ( rule__Assignment__ElementAssignment_1_0 )
                    // InternalLea.g:392:4: rule__Assignment__ElementAssignment_1_0
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
                    // InternalLea.g:396:2: ( ( rule__Assignment__OperationAssignment_1_1 ) )
                    {
                    // InternalLea.g:396:2: ( ( rule__Assignment__OperationAssignment_1_1 ) )
                    // InternalLea.g:397:3: ( rule__Assignment__OperationAssignment_1_1 )
                    {
                     before(grammarAccess.getAssignmentAccess().getOperationAssignment_1_1()); 
                    // InternalLea.g:398:3: ( rule__Assignment__OperationAssignment_1_1 )
                    // InternalLea.g:398:4: rule__Assignment__OperationAssignment_1_1
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
    // InternalLea.g:406:1: rule__Parameter__Alternatives : ( ( ( rule__Parameter__TextAssignment_0 ) ) | ( ( rule__Parameter__ElementAssignment_1 ) ) | ( ( rule__Parameter__OperationAssignment_2 ) ) );
    public final void rule__Parameter__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:410:1: ( ( ( rule__Parameter__TextAssignment_0 ) ) | ( ( rule__Parameter__ElementAssignment_1 ) ) | ( ( rule__Parameter__OperationAssignment_2 ) ) )
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_STRING) ) {
                alt3=1;
            }
            else if ( (LA3_0==RULE_ID) ) {
                int LA3_2 = input.LA(2);

                if ( ((LA3_2>=24 && LA3_2<=25)) ) {
                    alt3=3;
                }
                else if ( (LA3_2==EOF||LA3_2==14||LA3_2==26) ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

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
                    // InternalLea.g:411:2: ( ( rule__Parameter__TextAssignment_0 ) )
                    {
                    // InternalLea.g:411:2: ( ( rule__Parameter__TextAssignment_0 ) )
                    // InternalLea.g:412:3: ( rule__Parameter__TextAssignment_0 )
                    {
                     before(grammarAccess.getParameterAccess().getTextAssignment_0()); 
                    // InternalLea.g:413:3: ( rule__Parameter__TextAssignment_0 )
                    // InternalLea.g:413:4: rule__Parameter__TextAssignment_0
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
                    // InternalLea.g:417:2: ( ( rule__Parameter__ElementAssignment_1 ) )
                    {
                    // InternalLea.g:417:2: ( ( rule__Parameter__ElementAssignment_1 ) )
                    // InternalLea.g:418:3: ( rule__Parameter__ElementAssignment_1 )
                    {
                     before(grammarAccess.getParameterAccess().getElementAssignment_1()); 
                    // InternalLea.g:419:3: ( rule__Parameter__ElementAssignment_1 )
                    // InternalLea.g:419:4: rule__Parameter__ElementAssignment_1
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
                    // InternalLea.g:423:2: ( ( rule__Parameter__OperationAssignment_2 ) )
                    {
                    // InternalLea.g:423:2: ( ( rule__Parameter__OperationAssignment_2 ) )
                    // InternalLea.g:424:3: ( rule__Parameter__OperationAssignment_2 )
                    {
                     before(grammarAccess.getParameterAccess().getOperationAssignment_2()); 
                    // InternalLea.g:425:3: ( rule__Parameter__OperationAssignment_2 )
                    // InternalLea.g:425:4: rule__Parameter__OperationAssignment_2
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
    // InternalLea.g:433:1: rule__AnalysisDefinition__Group__0 : rule__AnalysisDefinition__Group__0__Impl rule__AnalysisDefinition__Group__1 ;
    public final void rule__AnalysisDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:437:1: ( rule__AnalysisDefinition__Group__0__Impl rule__AnalysisDefinition__Group__1 )
            // InternalLea.g:438:2: rule__AnalysisDefinition__Group__0__Impl rule__AnalysisDefinition__Group__1
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
    // InternalLea.g:445:1: rule__AnalysisDefinition__Group__0__Impl : ( () ) ;
    public final void rule__AnalysisDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:449:1: ( ( () ) )
            // InternalLea.g:450:1: ( () )
            {
            // InternalLea.g:450:1: ( () )
            // InternalLea.g:451:2: ()
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getAnalysisDefinitionAction_0()); 
            // InternalLea.g:452:2: ()
            // InternalLea.g:452:3: 
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
    // InternalLea.g:460:1: rule__AnalysisDefinition__Group__1 : rule__AnalysisDefinition__Group__1__Impl ;
    public final void rule__AnalysisDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:464:1: ( rule__AnalysisDefinition__Group__1__Impl )
            // InternalLea.g:465:2: rule__AnalysisDefinition__Group__1__Impl
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
    // InternalLea.g:471:1: rule__AnalysisDefinition__Group__1__Impl : ( ( rule__AnalysisDefinition__Alternatives_1 )* ) ;
    public final void rule__AnalysisDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:475:1: ( ( ( rule__AnalysisDefinition__Alternatives_1 )* ) )
            // InternalLea.g:476:1: ( ( rule__AnalysisDefinition__Alternatives_1 )* )
            {
            // InternalLea.g:476:1: ( ( rule__AnalysisDefinition__Alternatives_1 )* )
            // InternalLea.g:477:2: ( rule__AnalysisDefinition__Alternatives_1 )*
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getAlternatives_1()); 
            // InternalLea.g:478:2: ( rule__AnalysisDefinition__Alternatives_1 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==11||LA4_0==15||(LA4_0>=18 && LA4_0<=19)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalLea.g:478:3: rule__AnalysisDefinition__Alternatives_1
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__AnalysisDefinition__Alternatives_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
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
    // InternalLea.g:487:1: rule__ChangeIdentifierAssignment__Group__0 : rule__ChangeIdentifierAssignment__Group__0__Impl rule__ChangeIdentifierAssignment__Group__1 ;
    public final void rule__ChangeIdentifierAssignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:491:1: ( rule__ChangeIdentifierAssignment__Group__0__Impl rule__ChangeIdentifierAssignment__Group__1 )
            // InternalLea.g:492:2: rule__ChangeIdentifierAssignment__Group__0__Impl rule__ChangeIdentifierAssignment__Group__1
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
    // InternalLea.g:499:1: rule__ChangeIdentifierAssignment__Group__0__Impl : ( 'assign' ) ;
    public final void rule__ChangeIdentifierAssignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:503:1: ( ( 'assign' ) )
            // InternalLea.g:504:1: ( 'assign' )
            {
            // InternalLea.g:504:1: ( 'assign' )
            // InternalLea.g:505:2: 'assign'
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getAssignKeyword_0()); 
            match(input,11,FOLLOW_2); 
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
    // InternalLea.g:514:1: rule__ChangeIdentifierAssignment__Group__1 : rule__ChangeIdentifierAssignment__Group__1__Impl rule__ChangeIdentifierAssignment__Group__2 ;
    public final void rule__ChangeIdentifierAssignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:518:1: ( rule__ChangeIdentifierAssignment__Group__1__Impl rule__ChangeIdentifierAssignment__Group__2 )
            // InternalLea.g:519:2: rule__ChangeIdentifierAssignment__Group__1__Impl rule__ChangeIdentifierAssignment__Group__2
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
    // InternalLea.g:526:1: rule__ChangeIdentifierAssignment__Group__1__Impl : ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) ) ;
    public final void rule__ChangeIdentifierAssignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:530:1: ( ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) ) )
            // InternalLea.g:531:1: ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) )
            {
            // InternalLea.g:531:1: ( ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 ) )
            // InternalLea.g:532:2: ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 )
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getIdentifierAssignment_1()); 
            // InternalLea.g:533:2: ( rule__ChangeIdentifierAssignment__IdentifierAssignment_1 )
            // InternalLea.g:533:3: rule__ChangeIdentifierAssignment__IdentifierAssignment_1
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
    // InternalLea.g:541:1: rule__ChangeIdentifierAssignment__Group__2 : rule__ChangeIdentifierAssignment__Group__2__Impl rule__ChangeIdentifierAssignment__Group__3 ;
    public final void rule__ChangeIdentifierAssignment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:545:1: ( rule__ChangeIdentifierAssignment__Group__2__Impl rule__ChangeIdentifierAssignment__Group__3 )
            // InternalLea.g:546:2: rule__ChangeIdentifierAssignment__Group__2__Impl rule__ChangeIdentifierAssignment__Group__3
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
    // InternalLea.g:553:1: rule__ChangeIdentifierAssignment__Group__2__Impl : ( 'to' ) ;
    public final void rule__ChangeIdentifierAssignment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:557:1: ( ( 'to' ) )
            // InternalLea.g:558:1: ( 'to' )
            {
            // InternalLea.g:558:1: ( 'to' )
            // InternalLea.g:559:2: 'to'
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getToKeyword_2()); 
            match(input,12,FOLLOW_2); 
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
    // InternalLea.g:568:1: rule__ChangeIdentifierAssignment__Group__3 : rule__ChangeIdentifierAssignment__Group__3__Impl rule__ChangeIdentifierAssignment__Group__4 ;
    public final void rule__ChangeIdentifierAssignment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:572:1: ( rule__ChangeIdentifierAssignment__Group__3__Impl rule__ChangeIdentifierAssignment__Group__4 )
            // InternalLea.g:573:2: rule__ChangeIdentifierAssignment__Group__3__Impl rule__ChangeIdentifierAssignment__Group__4
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
    // InternalLea.g:580:1: rule__ChangeIdentifierAssignment__Group__3__Impl : ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) ) ;
    public final void rule__ChangeIdentifierAssignment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:584:1: ( ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) ) )
            // InternalLea.g:585:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) )
            {
            // InternalLea.g:585:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 ) )
            // InternalLea.g:586:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 )
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsAssignment_3()); 
            // InternalLea.g:587:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_3 )
            // InternalLea.g:587:3: rule__ChangeIdentifierAssignment__ElementsAssignment_3
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
    // InternalLea.g:595:1: rule__ChangeIdentifierAssignment__Group__4 : rule__ChangeIdentifierAssignment__Group__4__Impl rule__ChangeIdentifierAssignment__Group__5 ;
    public final void rule__ChangeIdentifierAssignment__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:599:1: ( rule__ChangeIdentifierAssignment__Group__4__Impl rule__ChangeIdentifierAssignment__Group__5 )
            // InternalLea.g:600:2: rule__ChangeIdentifierAssignment__Group__4__Impl rule__ChangeIdentifierAssignment__Group__5
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
    // InternalLea.g:607:1: rule__ChangeIdentifierAssignment__Group__4__Impl : ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* ) ;
    public final void rule__ChangeIdentifierAssignment__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:611:1: ( ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* ) )
            // InternalLea.g:612:1: ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* )
            {
            // InternalLea.g:612:1: ( ( rule__ChangeIdentifierAssignment__Group_4__0 )* )
            // InternalLea.g:613:2: ( rule__ChangeIdentifierAssignment__Group_4__0 )*
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getGroup_4()); 
            // InternalLea.g:614:2: ( rule__ChangeIdentifierAssignment__Group_4__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==14) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalLea.g:614:3: rule__ChangeIdentifierAssignment__Group_4__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__ChangeIdentifierAssignment__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
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
    // InternalLea.g:622:1: rule__ChangeIdentifierAssignment__Group__5 : rule__ChangeIdentifierAssignment__Group__5__Impl ;
    public final void rule__ChangeIdentifierAssignment__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:626:1: ( rule__ChangeIdentifierAssignment__Group__5__Impl )
            // InternalLea.g:627:2: rule__ChangeIdentifierAssignment__Group__5__Impl
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
    // InternalLea.g:633:1: rule__ChangeIdentifierAssignment__Group__5__Impl : ( ( ';' )? ) ;
    public final void rule__ChangeIdentifierAssignment__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:637:1: ( ( ( ';' )? ) )
            // InternalLea.g:638:1: ( ( ';' )? )
            {
            // InternalLea.g:638:1: ( ( ';' )? )
            // InternalLea.g:639:2: ( ';' )?
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getSemicolonKeyword_5()); 
            // InternalLea.g:640:2: ( ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==13) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalLea.g:640:3: ';'
                    {
                    match(input,13,FOLLOW_2); 

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
    // InternalLea.g:649:1: rule__ChangeIdentifierAssignment__Group_4__0 : rule__ChangeIdentifierAssignment__Group_4__0__Impl rule__ChangeIdentifierAssignment__Group_4__1 ;
    public final void rule__ChangeIdentifierAssignment__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:653:1: ( rule__ChangeIdentifierAssignment__Group_4__0__Impl rule__ChangeIdentifierAssignment__Group_4__1 )
            // InternalLea.g:654:2: rule__ChangeIdentifierAssignment__Group_4__0__Impl rule__ChangeIdentifierAssignment__Group_4__1
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
    // InternalLea.g:661:1: rule__ChangeIdentifierAssignment__Group_4__0__Impl : ( ',' ) ;
    public final void rule__ChangeIdentifierAssignment__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:665:1: ( ( ',' ) )
            // InternalLea.g:666:1: ( ',' )
            {
            // InternalLea.g:666:1: ( ',' )
            // InternalLea.g:667:2: ','
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getCommaKeyword_4_0()); 
            match(input,14,FOLLOW_2); 
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
    // InternalLea.g:676:1: rule__ChangeIdentifierAssignment__Group_4__1 : rule__ChangeIdentifierAssignment__Group_4__1__Impl ;
    public final void rule__ChangeIdentifierAssignment__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:680:1: ( rule__ChangeIdentifierAssignment__Group_4__1__Impl )
            // InternalLea.g:681:2: rule__ChangeIdentifierAssignment__Group_4__1__Impl
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
    // InternalLea.g:687:1: rule__ChangeIdentifierAssignment__Group_4__1__Impl : ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) ) ;
    public final void rule__ChangeIdentifierAssignment__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:691:1: ( ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) ) )
            // InternalLea.g:692:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) )
            {
            // InternalLea.g:692:1: ( ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 ) )
            // InternalLea.g:693:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 )
            {
             before(grammarAccess.getChangeIdentifierAssignmentAccess().getElementsAssignment_4_1()); 
            // InternalLea.g:694:2: ( rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 )
            // InternalLea.g:694:3: rule__ChangeIdentifierAssignment__ElementsAssignment_4_1
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


    // $ANTLR start "rule__ArtifactDeclaration__Group__0"
    // InternalLea.g:703:1: rule__ArtifactDeclaration__Group__0 : rule__ArtifactDeclaration__Group__0__Impl rule__ArtifactDeclaration__Group__1 ;
    public final void rule__ArtifactDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:707:1: ( rule__ArtifactDeclaration__Group__0__Impl rule__ArtifactDeclaration__Group__1 )
            // InternalLea.g:708:2: rule__ArtifactDeclaration__Group__0__Impl rule__ArtifactDeclaration__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ArtifactDeclaration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__1();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__0"


    // $ANTLR start "rule__ArtifactDeclaration__Group__0__Impl"
    // InternalLea.g:715:1: rule__ArtifactDeclaration__Group__0__Impl : ( 'Artifact' ) ;
    public final void rule__ArtifactDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:719:1: ( ( 'Artifact' ) )
            // InternalLea.g:720:1: ( 'Artifact' )
            {
            // InternalLea.g:720:1: ( 'Artifact' )
            // InternalLea.g:721:2: 'Artifact'
            {
             before(grammarAccess.getArtifactDeclarationAccess().getArtifactKeyword_0()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getArtifactDeclarationAccess().getArtifactKeyword_0()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__0__Impl"


    // $ANTLR start "rule__ArtifactDeclaration__Group__1"
    // InternalLea.g:730:1: rule__ArtifactDeclaration__Group__1 : rule__ArtifactDeclaration__Group__1__Impl rule__ArtifactDeclaration__Group__2 ;
    public final void rule__ArtifactDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:734:1: ( rule__ArtifactDeclaration__Group__1__Impl rule__ArtifactDeclaration__Group__2 )
            // InternalLea.g:735:2: rule__ArtifactDeclaration__Group__1__Impl rule__ArtifactDeclaration__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__ArtifactDeclaration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__2();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__1"


    // $ANTLR start "rule__ArtifactDeclaration__Group__1__Impl"
    // InternalLea.g:742:1: rule__ArtifactDeclaration__Group__1__Impl : ( '<' ) ;
    public final void rule__ArtifactDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:746:1: ( ( '<' ) )
            // InternalLea.g:747:1: ( '<' )
            {
            // InternalLea.g:747:1: ( '<' )
            // InternalLea.g:748:2: '<'
            {
             before(grammarAccess.getArtifactDeclarationAccess().getLessThanSignKeyword_1()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getArtifactDeclarationAccess().getLessThanSignKeyword_1()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__1__Impl"


    // $ANTLR start "rule__ArtifactDeclaration__Group__2"
    // InternalLea.g:757:1: rule__ArtifactDeclaration__Group__2 : rule__ArtifactDeclaration__Group__2__Impl rule__ArtifactDeclaration__Group__3 ;
    public final void rule__ArtifactDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:761:1: ( rule__ArtifactDeclaration__Group__2__Impl rule__ArtifactDeclaration__Group__3 )
            // InternalLea.g:762:2: rule__ArtifactDeclaration__Group__2__Impl rule__ArtifactDeclaration__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__ArtifactDeclaration__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__3();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__2"


    // $ANTLR start "rule__ArtifactDeclaration__Group__2__Impl"
    // InternalLea.g:769:1: rule__ArtifactDeclaration__Group__2__Impl : ( ( rule__ArtifactDeclaration__TypeAssignment_2 ) ) ;
    public final void rule__ArtifactDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:773:1: ( ( ( rule__ArtifactDeclaration__TypeAssignment_2 ) ) )
            // InternalLea.g:774:1: ( ( rule__ArtifactDeclaration__TypeAssignment_2 ) )
            {
            // InternalLea.g:774:1: ( ( rule__ArtifactDeclaration__TypeAssignment_2 ) )
            // InternalLea.g:775:2: ( rule__ArtifactDeclaration__TypeAssignment_2 )
            {
             before(grammarAccess.getArtifactDeclarationAccess().getTypeAssignment_2()); 
            // InternalLea.g:776:2: ( rule__ArtifactDeclaration__TypeAssignment_2 )
            // InternalLea.g:776:3: rule__ArtifactDeclaration__TypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__TypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getArtifactDeclarationAccess().getTypeAssignment_2()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__2__Impl"


    // $ANTLR start "rule__ArtifactDeclaration__Group__3"
    // InternalLea.g:784:1: rule__ArtifactDeclaration__Group__3 : rule__ArtifactDeclaration__Group__3__Impl rule__ArtifactDeclaration__Group__4 ;
    public final void rule__ArtifactDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:788:1: ( rule__ArtifactDeclaration__Group__3__Impl rule__ArtifactDeclaration__Group__4 )
            // InternalLea.g:789:2: rule__ArtifactDeclaration__Group__3__Impl rule__ArtifactDeclaration__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__ArtifactDeclaration__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__4();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__3"


    // $ANTLR start "rule__ArtifactDeclaration__Group__3__Impl"
    // InternalLea.g:796:1: rule__ArtifactDeclaration__Group__3__Impl : ( '>' ) ;
    public final void rule__ArtifactDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:800:1: ( ( '>' ) )
            // InternalLea.g:801:1: ( '>' )
            {
            // InternalLea.g:801:1: ( '>' )
            // InternalLea.g:802:2: '>'
            {
             before(grammarAccess.getArtifactDeclarationAccess().getGreaterThanSignKeyword_3()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getArtifactDeclarationAccess().getGreaterThanSignKeyword_3()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__3__Impl"


    // $ANTLR start "rule__ArtifactDeclaration__Group__4"
    // InternalLea.g:811:1: rule__ArtifactDeclaration__Group__4 : rule__ArtifactDeclaration__Group__4__Impl rule__ArtifactDeclaration__Group__5 ;
    public final void rule__ArtifactDeclaration__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:815:1: ( rule__ArtifactDeclaration__Group__4__Impl rule__ArtifactDeclaration__Group__5 )
            // InternalLea.g:816:2: rule__ArtifactDeclaration__Group__4__Impl rule__ArtifactDeclaration__Group__5
            {
            pushFollow(FOLLOW_11);
            rule__ArtifactDeclaration__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__5();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__4"


    // $ANTLR start "rule__ArtifactDeclaration__Group__4__Impl"
    // InternalLea.g:823:1: rule__ArtifactDeclaration__Group__4__Impl : ( ( rule__ArtifactDeclaration__SetAssignment_4 )? ) ;
    public final void rule__ArtifactDeclaration__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:827:1: ( ( ( rule__ArtifactDeclaration__SetAssignment_4 )? ) )
            // InternalLea.g:828:1: ( ( rule__ArtifactDeclaration__SetAssignment_4 )? )
            {
            // InternalLea.g:828:1: ( ( rule__ArtifactDeclaration__SetAssignment_4 )? )
            // InternalLea.g:829:2: ( rule__ArtifactDeclaration__SetAssignment_4 )?
            {
             before(grammarAccess.getArtifactDeclarationAccess().getSetAssignment_4()); 
            // InternalLea.g:830:2: ( rule__ArtifactDeclaration__SetAssignment_4 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==20) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalLea.g:830:3: rule__ArtifactDeclaration__SetAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ArtifactDeclaration__SetAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getArtifactDeclarationAccess().getSetAssignment_4()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__4__Impl"


    // $ANTLR start "rule__ArtifactDeclaration__Group__5"
    // InternalLea.g:838:1: rule__ArtifactDeclaration__Group__5 : rule__ArtifactDeclaration__Group__5__Impl rule__ArtifactDeclaration__Group__6 ;
    public final void rule__ArtifactDeclaration__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:842:1: ( rule__ArtifactDeclaration__Group__5__Impl rule__ArtifactDeclaration__Group__6 )
            // InternalLea.g:843:2: rule__ArtifactDeclaration__Group__5__Impl rule__ArtifactDeclaration__Group__6
            {
            pushFollow(FOLLOW_12);
            rule__ArtifactDeclaration__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__6();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__5"


    // $ANTLR start "rule__ArtifactDeclaration__Group__5__Impl"
    // InternalLea.g:850:1: rule__ArtifactDeclaration__Group__5__Impl : ( ( rule__ArtifactDeclaration__NameAssignment_5 ) ) ;
    public final void rule__ArtifactDeclaration__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:854:1: ( ( ( rule__ArtifactDeclaration__NameAssignment_5 ) ) )
            // InternalLea.g:855:1: ( ( rule__ArtifactDeclaration__NameAssignment_5 ) )
            {
            // InternalLea.g:855:1: ( ( rule__ArtifactDeclaration__NameAssignment_5 ) )
            // InternalLea.g:856:2: ( rule__ArtifactDeclaration__NameAssignment_5 )
            {
             before(grammarAccess.getArtifactDeclarationAccess().getNameAssignment_5()); 
            // InternalLea.g:857:2: ( rule__ArtifactDeclaration__NameAssignment_5 )
            // InternalLea.g:857:3: rule__ArtifactDeclaration__NameAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__NameAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getArtifactDeclarationAccess().getNameAssignment_5()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__5__Impl"


    // $ANTLR start "rule__ArtifactDeclaration__Group__6"
    // InternalLea.g:865:1: rule__ArtifactDeclaration__Group__6 : rule__ArtifactDeclaration__Group__6__Impl rule__ArtifactDeclaration__Group__7 ;
    public final void rule__ArtifactDeclaration__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:869:1: ( rule__ArtifactDeclaration__Group__6__Impl rule__ArtifactDeclaration__Group__7 )
            // InternalLea.g:870:2: rule__ArtifactDeclaration__Group__6__Impl rule__ArtifactDeclaration__Group__7
            {
            pushFollow(FOLLOW_12);
            rule__ArtifactDeclaration__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__7();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__6"


    // $ANTLR start "rule__ArtifactDeclaration__Group__6__Impl"
    // InternalLea.g:877:1: rule__ArtifactDeclaration__Group__6__Impl : ( ( rule__ArtifactDeclaration__InitializationAssignment_6 )? ) ;
    public final void rule__ArtifactDeclaration__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:881:1: ( ( ( rule__ArtifactDeclaration__InitializationAssignment_6 )? ) )
            // InternalLea.g:882:1: ( ( rule__ArtifactDeclaration__InitializationAssignment_6 )? )
            {
            // InternalLea.g:882:1: ( ( rule__ArtifactDeclaration__InitializationAssignment_6 )? )
            // InternalLea.g:883:2: ( rule__ArtifactDeclaration__InitializationAssignment_6 )?
            {
             before(grammarAccess.getArtifactDeclarationAccess().getInitializationAssignment_6()); 
            // InternalLea.g:884:2: ( rule__ArtifactDeclaration__InitializationAssignment_6 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalLea.g:884:3: rule__ArtifactDeclaration__InitializationAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__ArtifactDeclaration__InitializationAssignment_6();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getArtifactDeclarationAccess().getInitializationAssignment_6()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__6__Impl"


    // $ANTLR start "rule__ArtifactDeclaration__Group__7"
    // InternalLea.g:892:1: rule__ArtifactDeclaration__Group__7 : rule__ArtifactDeclaration__Group__7__Impl ;
    public final void rule__ArtifactDeclaration__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:896:1: ( rule__ArtifactDeclaration__Group__7__Impl )
            // InternalLea.g:897:2: rule__ArtifactDeclaration__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ArtifactDeclaration__Group__7__Impl();

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__7"


    // $ANTLR start "rule__ArtifactDeclaration__Group__7__Impl"
    // InternalLea.g:903:1: rule__ArtifactDeclaration__Group__7__Impl : ( ( ';' )? ) ;
    public final void rule__ArtifactDeclaration__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:907:1: ( ( ( ';' )? ) )
            // InternalLea.g:908:1: ( ( ';' )? )
            {
            // InternalLea.g:908:1: ( ( ';' )? )
            // InternalLea.g:909:2: ( ';' )?
            {
             before(grammarAccess.getArtifactDeclarationAccess().getSemicolonKeyword_7()); 
            // InternalLea.g:910:2: ( ';' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==13) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalLea.g:910:3: ';'
                    {
                    match(input,13,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getArtifactDeclarationAccess().getSemicolonKeyword_7()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__Group__7__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__0"
    // InternalLea.g:919:1: rule__FragmentDeclaration__Group__0 : rule__FragmentDeclaration__Group__0__Impl rule__FragmentDeclaration__Group__1 ;
    public final void rule__FragmentDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:923:1: ( rule__FragmentDeclaration__Group__0__Impl rule__FragmentDeclaration__Group__1 )
            // InternalLea.g:924:2: rule__FragmentDeclaration__Group__0__Impl rule__FragmentDeclaration__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__FragmentDeclaration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__1();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__0"


    // $ANTLR start "rule__FragmentDeclaration__Group__0__Impl"
    // InternalLea.g:931:1: rule__FragmentDeclaration__Group__0__Impl : ( 'Fragment' ) ;
    public final void rule__FragmentDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:935:1: ( ( 'Fragment' ) )
            // InternalLea.g:936:1: ( 'Fragment' )
            {
            // InternalLea.g:936:1: ( 'Fragment' )
            // InternalLea.g:937:2: 'Fragment'
            {
             before(grammarAccess.getFragmentDeclarationAccess().getFragmentKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getFragmentDeclarationAccess().getFragmentKeyword_0()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__0__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__1"
    // InternalLea.g:946:1: rule__FragmentDeclaration__Group__1 : rule__FragmentDeclaration__Group__1__Impl rule__FragmentDeclaration__Group__2 ;
    public final void rule__FragmentDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:950:1: ( rule__FragmentDeclaration__Group__1__Impl rule__FragmentDeclaration__Group__2 )
            // InternalLea.g:951:2: rule__FragmentDeclaration__Group__1__Impl rule__FragmentDeclaration__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__FragmentDeclaration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__2();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__1"


    // $ANTLR start "rule__FragmentDeclaration__Group__1__Impl"
    // InternalLea.g:958:1: rule__FragmentDeclaration__Group__1__Impl : ( '<' ) ;
    public final void rule__FragmentDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:962:1: ( ( '<' ) )
            // InternalLea.g:963:1: ( '<' )
            {
            // InternalLea.g:963:1: ( '<' )
            // InternalLea.g:964:2: '<'
            {
             before(grammarAccess.getFragmentDeclarationAccess().getLessThanSignKeyword_1()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getFragmentDeclarationAccess().getLessThanSignKeyword_1()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__1__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__2"
    // InternalLea.g:973:1: rule__FragmentDeclaration__Group__2 : rule__FragmentDeclaration__Group__2__Impl rule__FragmentDeclaration__Group__3 ;
    public final void rule__FragmentDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:977:1: ( rule__FragmentDeclaration__Group__2__Impl rule__FragmentDeclaration__Group__3 )
            // InternalLea.g:978:2: rule__FragmentDeclaration__Group__2__Impl rule__FragmentDeclaration__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__FragmentDeclaration__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__3();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__2"


    // $ANTLR start "rule__FragmentDeclaration__Group__2__Impl"
    // InternalLea.g:985:1: rule__FragmentDeclaration__Group__2__Impl : ( ( rule__FragmentDeclaration__TypeAssignment_2 ) ) ;
    public final void rule__FragmentDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:989:1: ( ( ( rule__FragmentDeclaration__TypeAssignment_2 ) ) )
            // InternalLea.g:990:1: ( ( rule__FragmentDeclaration__TypeAssignment_2 ) )
            {
            // InternalLea.g:990:1: ( ( rule__FragmentDeclaration__TypeAssignment_2 ) )
            // InternalLea.g:991:2: ( rule__FragmentDeclaration__TypeAssignment_2 )
            {
             before(grammarAccess.getFragmentDeclarationAccess().getTypeAssignment_2()); 
            // InternalLea.g:992:2: ( rule__FragmentDeclaration__TypeAssignment_2 )
            // InternalLea.g:992:3: rule__FragmentDeclaration__TypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__TypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFragmentDeclarationAccess().getTypeAssignment_2()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__2__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__3"
    // InternalLea.g:1000:1: rule__FragmentDeclaration__Group__3 : rule__FragmentDeclaration__Group__3__Impl rule__FragmentDeclaration__Group__4 ;
    public final void rule__FragmentDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1004:1: ( rule__FragmentDeclaration__Group__3__Impl rule__FragmentDeclaration__Group__4 )
            // InternalLea.g:1005:2: rule__FragmentDeclaration__Group__3__Impl rule__FragmentDeclaration__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__FragmentDeclaration__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__4();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__3"


    // $ANTLR start "rule__FragmentDeclaration__Group__3__Impl"
    // InternalLea.g:1012:1: rule__FragmentDeclaration__Group__3__Impl : ( '>' ) ;
    public final void rule__FragmentDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1016:1: ( ( '>' ) )
            // InternalLea.g:1017:1: ( '>' )
            {
            // InternalLea.g:1017:1: ( '>' )
            // InternalLea.g:1018:2: '>'
            {
             before(grammarAccess.getFragmentDeclarationAccess().getGreaterThanSignKeyword_3()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getFragmentDeclarationAccess().getGreaterThanSignKeyword_3()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__3__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__4"
    // InternalLea.g:1027:1: rule__FragmentDeclaration__Group__4 : rule__FragmentDeclaration__Group__4__Impl rule__FragmentDeclaration__Group__5 ;
    public final void rule__FragmentDeclaration__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1031:1: ( rule__FragmentDeclaration__Group__4__Impl rule__FragmentDeclaration__Group__5 )
            // InternalLea.g:1032:2: rule__FragmentDeclaration__Group__4__Impl rule__FragmentDeclaration__Group__5
            {
            pushFollow(FOLLOW_11);
            rule__FragmentDeclaration__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__5();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__4"


    // $ANTLR start "rule__FragmentDeclaration__Group__4__Impl"
    // InternalLea.g:1039:1: rule__FragmentDeclaration__Group__4__Impl : ( ( rule__FragmentDeclaration__SetAssignment_4 )? ) ;
    public final void rule__FragmentDeclaration__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1043:1: ( ( ( rule__FragmentDeclaration__SetAssignment_4 )? ) )
            // InternalLea.g:1044:1: ( ( rule__FragmentDeclaration__SetAssignment_4 )? )
            {
            // InternalLea.g:1044:1: ( ( rule__FragmentDeclaration__SetAssignment_4 )? )
            // InternalLea.g:1045:2: ( rule__FragmentDeclaration__SetAssignment_4 )?
            {
             before(grammarAccess.getFragmentDeclarationAccess().getSetAssignment_4()); 
            // InternalLea.g:1046:2: ( rule__FragmentDeclaration__SetAssignment_4 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==20) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalLea.g:1046:3: rule__FragmentDeclaration__SetAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__FragmentDeclaration__SetAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFragmentDeclarationAccess().getSetAssignment_4()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__4__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__5"
    // InternalLea.g:1054:1: rule__FragmentDeclaration__Group__5 : rule__FragmentDeclaration__Group__5__Impl rule__FragmentDeclaration__Group__6 ;
    public final void rule__FragmentDeclaration__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1058:1: ( rule__FragmentDeclaration__Group__5__Impl rule__FragmentDeclaration__Group__6 )
            // InternalLea.g:1059:2: rule__FragmentDeclaration__Group__5__Impl rule__FragmentDeclaration__Group__6
            {
            pushFollow(FOLLOW_12);
            rule__FragmentDeclaration__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__6();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__5"


    // $ANTLR start "rule__FragmentDeclaration__Group__5__Impl"
    // InternalLea.g:1066:1: rule__FragmentDeclaration__Group__5__Impl : ( ( rule__FragmentDeclaration__NameAssignment_5 ) ) ;
    public final void rule__FragmentDeclaration__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1070:1: ( ( ( rule__FragmentDeclaration__NameAssignment_5 ) ) )
            // InternalLea.g:1071:1: ( ( rule__FragmentDeclaration__NameAssignment_5 ) )
            {
            // InternalLea.g:1071:1: ( ( rule__FragmentDeclaration__NameAssignment_5 ) )
            // InternalLea.g:1072:2: ( rule__FragmentDeclaration__NameAssignment_5 )
            {
             before(grammarAccess.getFragmentDeclarationAccess().getNameAssignment_5()); 
            // InternalLea.g:1073:2: ( rule__FragmentDeclaration__NameAssignment_5 )
            // InternalLea.g:1073:3: rule__FragmentDeclaration__NameAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__NameAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getFragmentDeclarationAccess().getNameAssignment_5()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__5__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__6"
    // InternalLea.g:1081:1: rule__FragmentDeclaration__Group__6 : rule__FragmentDeclaration__Group__6__Impl rule__FragmentDeclaration__Group__7 ;
    public final void rule__FragmentDeclaration__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1085:1: ( rule__FragmentDeclaration__Group__6__Impl rule__FragmentDeclaration__Group__7 )
            // InternalLea.g:1086:2: rule__FragmentDeclaration__Group__6__Impl rule__FragmentDeclaration__Group__7
            {
            pushFollow(FOLLOW_12);
            rule__FragmentDeclaration__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__7();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__6"


    // $ANTLR start "rule__FragmentDeclaration__Group__6__Impl"
    // InternalLea.g:1093:1: rule__FragmentDeclaration__Group__6__Impl : ( ( rule__FragmentDeclaration__InitializationAssignment_6 )? ) ;
    public final void rule__FragmentDeclaration__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1097:1: ( ( ( rule__FragmentDeclaration__InitializationAssignment_6 )? ) )
            // InternalLea.g:1098:1: ( ( rule__FragmentDeclaration__InitializationAssignment_6 )? )
            {
            // InternalLea.g:1098:1: ( ( rule__FragmentDeclaration__InitializationAssignment_6 )? )
            // InternalLea.g:1099:2: ( rule__FragmentDeclaration__InitializationAssignment_6 )?
            {
             before(grammarAccess.getFragmentDeclarationAccess().getInitializationAssignment_6()); 
            // InternalLea.g:1100:2: ( rule__FragmentDeclaration__InitializationAssignment_6 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLea.g:1100:3: rule__FragmentDeclaration__InitializationAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__FragmentDeclaration__InitializationAssignment_6();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFragmentDeclarationAccess().getInitializationAssignment_6()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__6__Impl"


    // $ANTLR start "rule__FragmentDeclaration__Group__7"
    // InternalLea.g:1108:1: rule__FragmentDeclaration__Group__7 : rule__FragmentDeclaration__Group__7__Impl ;
    public final void rule__FragmentDeclaration__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1112:1: ( rule__FragmentDeclaration__Group__7__Impl )
            // InternalLea.g:1113:2: rule__FragmentDeclaration__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FragmentDeclaration__Group__7__Impl();

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
    // $ANTLR end "rule__FragmentDeclaration__Group__7"


    // $ANTLR start "rule__FragmentDeclaration__Group__7__Impl"
    // InternalLea.g:1119:1: rule__FragmentDeclaration__Group__7__Impl : ( ( ';' )? ) ;
    public final void rule__FragmentDeclaration__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1123:1: ( ( ( ';' )? ) )
            // InternalLea.g:1124:1: ( ( ';' )? )
            {
            // InternalLea.g:1124:1: ( ( ';' )? )
            // InternalLea.g:1125:2: ( ';' )?
            {
             before(grammarAccess.getFragmentDeclarationAccess().getSemicolonKeyword_7()); 
            // InternalLea.g:1126:2: ( ';' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==13) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalLea.g:1126:3: ';'
                    {
                    match(input,13,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getFragmentDeclarationAccess().getSemicolonKeyword_7()); 

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
    // $ANTLR end "rule__FragmentDeclaration__Group__7__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__0"
    // InternalLea.g:1135:1: rule__ResultDeclaration__Group__0 : rule__ResultDeclaration__Group__0__Impl rule__ResultDeclaration__Group__1 ;
    public final void rule__ResultDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1139:1: ( rule__ResultDeclaration__Group__0__Impl rule__ResultDeclaration__Group__1 )
            // InternalLea.g:1140:2: rule__ResultDeclaration__Group__0__Impl rule__ResultDeclaration__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ResultDeclaration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__1();

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
    // $ANTLR end "rule__ResultDeclaration__Group__0"


    // $ANTLR start "rule__ResultDeclaration__Group__0__Impl"
    // InternalLea.g:1147:1: rule__ResultDeclaration__Group__0__Impl : ( 'Result' ) ;
    public final void rule__ResultDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1151:1: ( ( 'Result' ) )
            // InternalLea.g:1152:1: ( 'Result' )
            {
            // InternalLea.g:1152:1: ( 'Result' )
            // InternalLea.g:1153:2: 'Result'
            {
             before(grammarAccess.getResultDeclarationAccess().getResultKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getResultDeclarationAccess().getResultKeyword_0()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__0__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__1"
    // InternalLea.g:1162:1: rule__ResultDeclaration__Group__1 : rule__ResultDeclaration__Group__1__Impl rule__ResultDeclaration__Group__2 ;
    public final void rule__ResultDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1166:1: ( rule__ResultDeclaration__Group__1__Impl rule__ResultDeclaration__Group__2 )
            // InternalLea.g:1167:2: rule__ResultDeclaration__Group__1__Impl rule__ResultDeclaration__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__ResultDeclaration__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__2();

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
    // $ANTLR end "rule__ResultDeclaration__Group__1"


    // $ANTLR start "rule__ResultDeclaration__Group__1__Impl"
    // InternalLea.g:1174:1: rule__ResultDeclaration__Group__1__Impl : ( '<' ) ;
    public final void rule__ResultDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1178:1: ( ( '<' ) )
            // InternalLea.g:1179:1: ( '<' )
            {
            // InternalLea.g:1179:1: ( '<' )
            // InternalLea.g:1180:2: '<'
            {
             before(grammarAccess.getResultDeclarationAccess().getLessThanSignKeyword_1()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getResultDeclarationAccess().getLessThanSignKeyword_1()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__1__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__2"
    // InternalLea.g:1189:1: rule__ResultDeclaration__Group__2 : rule__ResultDeclaration__Group__2__Impl rule__ResultDeclaration__Group__3 ;
    public final void rule__ResultDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1193:1: ( rule__ResultDeclaration__Group__2__Impl rule__ResultDeclaration__Group__3 )
            // InternalLea.g:1194:2: rule__ResultDeclaration__Group__2__Impl rule__ResultDeclaration__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__ResultDeclaration__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__3();

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
    // $ANTLR end "rule__ResultDeclaration__Group__2"


    // $ANTLR start "rule__ResultDeclaration__Group__2__Impl"
    // InternalLea.g:1201:1: rule__ResultDeclaration__Group__2__Impl : ( ( rule__ResultDeclaration__TypeAssignment_2 ) ) ;
    public final void rule__ResultDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1205:1: ( ( ( rule__ResultDeclaration__TypeAssignment_2 ) ) )
            // InternalLea.g:1206:1: ( ( rule__ResultDeclaration__TypeAssignment_2 ) )
            {
            // InternalLea.g:1206:1: ( ( rule__ResultDeclaration__TypeAssignment_2 ) )
            // InternalLea.g:1207:2: ( rule__ResultDeclaration__TypeAssignment_2 )
            {
             before(grammarAccess.getResultDeclarationAccess().getTypeAssignment_2()); 
            // InternalLea.g:1208:2: ( rule__ResultDeclaration__TypeAssignment_2 )
            // InternalLea.g:1208:3: rule__ResultDeclaration__TypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__TypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getResultDeclarationAccess().getTypeAssignment_2()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__2__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__3"
    // InternalLea.g:1216:1: rule__ResultDeclaration__Group__3 : rule__ResultDeclaration__Group__3__Impl rule__ResultDeclaration__Group__4 ;
    public final void rule__ResultDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1220:1: ( rule__ResultDeclaration__Group__3__Impl rule__ResultDeclaration__Group__4 )
            // InternalLea.g:1221:2: rule__ResultDeclaration__Group__3__Impl rule__ResultDeclaration__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__ResultDeclaration__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__4();

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
    // $ANTLR end "rule__ResultDeclaration__Group__3"


    // $ANTLR start "rule__ResultDeclaration__Group__3__Impl"
    // InternalLea.g:1228:1: rule__ResultDeclaration__Group__3__Impl : ( '>' ) ;
    public final void rule__ResultDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1232:1: ( ( '>' ) )
            // InternalLea.g:1233:1: ( '>' )
            {
            // InternalLea.g:1233:1: ( '>' )
            // InternalLea.g:1234:2: '>'
            {
             before(grammarAccess.getResultDeclarationAccess().getGreaterThanSignKeyword_3()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getResultDeclarationAccess().getGreaterThanSignKeyword_3()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__3__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__4"
    // InternalLea.g:1243:1: rule__ResultDeclaration__Group__4 : rule__ResultDeclaration__Group__4__Impl rule__ResultDeclaration__Group__5 ;
    public final void rule__ResultDeclaration__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1247:1: ( rule__ResultDeclaration__Group__4__Impl rule__ResultDeclaration__Group__5 )
            // InternalLea.g:1248:2: rule__ResultDeclaration__Group__4__Impl rule__ResultDeclaration__Group__5
            {
            pushFollow(FOLLOW_11);
            rule__ResultDeclaration__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__5();

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
    // $ANTLR end "rule__ResultDeclaration__Group__4"


    // $ANTLR start "rule__ResultDeclaration__Group__4__Impl"
    // InternalLea.g:1255:1: rule__ResultDeclaration__Group__4__Impl : ( ( rule__ResultDeclaration__SetAssignment_4 )? ) ;
    public final void rule__ResultDeclaration__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1259:1: ( ( ( rule__ResultDeclaration__SetAssignment_4 )? ) )
            // InternalLea.g:1260:1: ( ( rule__ResultDeclaration__SetAssignment_4 )? )
            {
            // InternalLea.g:1260:1: ( ( rule__ResultDeclaration__SetAssignment_4 )? )
            // InternalLea.g:1261:2: ( rule__ResultDeclaration__SetAssignment_4 )?
            {
             before(grammarAccess.getResultDeclarationAccess().getSetAssignment_4()); 
            // InternalLea.g:1262:2: ( rule__ResultDeclaration__SetAssignment_4 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==20) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalLea.g:1262:3: rule__ResultDeclaration__SetAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ResultDeclaration__SetAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getResultDeclarationAccess().getSetAssignment_4()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__4__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__5"
    // InternalLea.g:1270:1: rule__ResultDeclaration__Group__5 : rule__ResultDeclaration__Group__5__Impl rule__ResultDeclaration__Group__6 ;
    public final void rule__ResultDeclaration__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1274:1: ( rule__ResultDeclaration__Group__5__Impl rule__ResultDeclaration__Group__6 )
            // InternalLea.g:1275:2: rule__ResultDeclaration__Group__5__Impl rule__ResultDeclaration__Group__6
            {
            pushFollow(FOLLOW_12);
            rule__ResultDeclaration__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__6();

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
    // $ANTLR end "rule__ResultDeclaration__Group__5"


    // $ANTLR start "rule__ResultDeclaration__Group__5__Impl"
    // InternalLea.g:1282:1: rule__ResultDeclaration__Group__5__Impl : ( ( rule__ResultDeclaration__NameAssignment_5 ) ) ;
    public final void rule__ResultDeclaration__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1286:1: ( ( ( rule__ResultDeclaration__NameAssignment_5 ) ) )
            // InternalLea.g:1287:1: ( ( rule__ResultDeclaration__NameAssignment_5 ) )
            {
            // InternalLea.g:1287:1: ( ( rule__ResultDeclaration__NameAssignment_5 ) )
            // InternalLea.g:1288:2: ( rule__ResultDeclaration__NameAssignment_5 )
            {
             before(grammarAccess.getResultDeclarationAccess().getNameAssignment_5()); 
            // InternalLea.g:1289:2: ( rule__ResultDeclaration__NameAssignment_5 )
            // InternalLea.g:1289:3: rule__ResultDeclaration__NameAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__NameAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getResultDeclarationAccess().getNameAssignment_5()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__5__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__6"
    // InternalLea.g:1297:1: rule__ResultDeclaration__Group__6 : rule__ResultDeclaration__Group__6__Impl rule__ResultDeclaration__Group__7 ;
    public final void rule__ResultDeclaration__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1301:1: ( rule__ResultDeclaration__Group__6__Impl rule__ResultDeclaration__Group__7 )
            // InternalLea.g:1302:2: rule__ResultDeclaration__Group__6__Impl rule__ResultDeclaration__Group__7
            {
            pushFollow(FOLLOW_12);
            rule__ResultDeclaration__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__7();

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
    // $ANTLR end "rule__ResultDeclaration__Group__6"


    // $ANTLR start "rule__ResultDeclaration__Group__6__Impl"
    // InternalLea.g:1309:1: rule__ResultDeclaration__Group__6__Impl : ( ( rule__ResultDeclaration__InitializationAssignment_6 )? ) ;
    public final void rule__ResultDeclaration__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1313:1: ( ( ( rule__ResultDeclaration__InitializationAssignment_6 )? ) )
            // InternalLea.g:1314:1: ( ( rule__ResultDeclaration__InitializationAssignment_6 )? )
            {
            // InternalLea.g:1314:1: ( ( rule__ResultDeclaration__InitializationAssignment_6 )? )
            // InternalLea.g:1315:2: ( rule__ResultDeclaration__InitializationAssignment_6 )?
            {
             before(grammarAccess.getResultDeclarationAccess().getInitializationAssignment_6()); 
            // InternalLea.g:1316:2: ( rule__ResultDeclaration__InitializationAssignment_6 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==23) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalLea.g:1316:3: rule__ResultDeclaration__InitializationAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__ResultDeclaration__InitializationAssignment_6();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getResultDeclarationAccess().getInitializationAssignment_6()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__6__Impl"


    // $ANTLR start "rule__ResultDeclaration__Group__7"
    // InternalLea.g:1324:1: rule__ResultDeclaration__Group__7 : rule__ResultDeclaration__Group__7__Impl ;
    public final void rule__ResultDeclaration__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1328:1: ( rule__ResultDeclaration__Group__7__Impl )
            // InternalLea.g:1329:2: rule__ResultDeclaration__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResultDeclaration__Group__7__Impl();

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
    // $ANTLR end "rule__ResultDeclaration__Group__7"


    // $ANTLR start "rule__ResultDeclaration__Group__7__Impl"
    // InternalLea.g:1335:1: rule__ResultDeclaration__Group__7__Impl : ( ( ';' )? ) ;
    public final void rule__ResultDeclaration__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1339:1: ( ( ( ';' )? ) )
            // InternalLea.g:1340:1: ( ( ';' )? )
            {
            // InternalLea.g:1340:1: ( ( ';' )? )
            // InternalLea.g:1341:2: ( ';' )?
            {
             before(grammarAccess.getResultDeclarationAccess().getSemicolonKeyword_7()); 
            // InternalLea.g:1342:2: ( ';' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==13) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalLea.g:1342:3: ';'
                    {
                    match(input,13,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getResultDeclarationAccess().getSemicolonKeyword_7()); 

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
    // $ANTLR end "rule__ResultDeclaration__Group__7__Impl"


    // $ANTLR start "rule__SetDefinition__Group__0"
    // InternalLea.g:1351:1: rule__SetDefinition__Group__0 : rule__SetDefinition__Group__0__Impl rule__SetDefinition__Group__1 ;
    public final void rule__SetDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1355:1: ( rule__SetDefinition__Group__0__Impl rule__SetDefinition__Group__1 )
            // InternalLea.g:1356:2: rule__SetDefinition__Group__0__Impl rule__SetDefinition__Group__1
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
    // InternalLea.g:1363:1: rule__SetDefinition__Group__0__Impl : ( () ) ;
    public final void rule__SetDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1367:1: ( ( () ) )
            // InternalLea.g:1368:1: ( () )
            {
            // InternalLea.g:1368:1: ( () )
            // InternalLea.g:1369:2: ()
            {
             before(grammarAccess.getSetDefinitionAccess().getSetDefinitionAction_0()); 
            // InternalLea.g:1370:2: ()
            // InternalLea.g:1370:3: 
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
    // InternalLea.g:1378:1: rule__SetDefinition__Group__1 : rule__SetDefinition__Group__1__Impl rule__SetDefinition__Group__2 ;
    public final void rule__SetDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1382:1: ( rule__SetDefinition__Group__1__Impl rule__SetDefinition__Group__2 )
            // InternalLea.g:1383:2: rule__SetDefinition__Group__1__Impl rule__SetDefinition__Group__2
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
    // InternalLea.g:1390:1: rule__SetDefinition__Group__1__Impl : ( '[' ) ;
    public final void rule__SetDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1394:1: ( ( '[' ) )
            // InternalLea.g:1395:1: ( '[' )
            {
            // InternalLea.g:1395:1: ( '[' )
            // InternalLea.g:1396:2: '['
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
    // InternalLea.g:1405:1: rule__SetDefinition__Group__2 : rule__SetDefinition__Group__2__Impl rule__SetDefinition__Group__3 ;
    public final void rule__SetDefinition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1409:1: ( rule__SetDefinition__Group__2__Impl rule__SetDefinition__Group__3 )
            // InternalLea.g:1410:2: rule__SetDefinition__Group__2__Impl rule__SetDefinition__Group__3
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
    // InternalLea.g:1417:1: rule__SetDefinition__Group__2__Impl : ( ( rule__SetDefinition__IterationAssignment_2 )? ) ;
    public final void rule__SetDefinition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1421:1: ( ( ( rule__SetDefinition__IterationAssignment_2 )? ) )
            // InternalLea.g:1422:1: ( ( rule__SetDefinition__IterationAssignment_2 )? )
            {
            // InternalLea.g:1422:1: ( ( rule__SetDefinition__IterationAssignment_2 )? )
            // InternalLea.g:1423:2: ( rule__SetDefinition__IterationAssignment_2 )?
            {
             before(grammarAccess.getSetDefinitionAccess().getIterationAssignment_2()); 
            // InternalLea.g:1424:2: ( rule__SetDefinition__IterationAssignment_2 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalLea.g:1424:3: rule__SetDefinition__IterationAssignment_2
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
    // InternalLea.g:1432:1: rule__SetDefinition__Group__3 : rule__SetDefinition__Group__3__Impl ;
    public final void rule__SetDefinition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1436:1: ( rule__SetDefinition__Group__3__Impl )
            // InternalLea.g:1437:2: rule__SetDefinition__Group__3__Impl
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
    // InternalLea.g:1443:1: rule__SetDefinition__Group__3__Impl : ( ']' ) ;
    public final void rule__SetDefinition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1447:1: ( ( ']' ) )
            // InternalLea.g:1448:1: ( ']' )
            {
            // InternalLea.g:1448:1: ( ']' )
            // InternalLea.g:1449:2: ']'
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
    // InternalLea.g:1459:1: rule__Iteration__Group__0 : rule__Iteration__Group__0__Impl rule__Iteration__Group__1 ;
    public final void rule__Iteration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1463:1: ( rule__Iteration__Group__0__Impl rule__Iteration__Group__1 )
            // InternalLea.g:1464:2: rule__Iteration__Group__0__Impl rule__Iteration__Group__1
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
    // InternalLea.g:1471:1: rule__Iteration__Group__0__Impl : ( ( rule__Iteration__IteratorAssignment_0 ) ) ;
    public final void rule__Iteration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1475:1: ( ( ( rule__Iteration__IteratorAssignment_0 ) ) )
            // InternalLea.g:1476:1: ( ( rule__Iteration__IteratorAssignment_0 ) )
            {
            // InternalLea.g:1476:1: ( ( rule__Iteration__IteratorAssignment_0 ) )
            // InternalLea.g:1477:2: ( rule__Iteration__IteratorAssignment_0 )
            {
             before(grammarAccess.getIterationAccess().getIteratorAssignment_0()); 
            // InternalLea.g:1478:2: ( rule__Iteration__IteratorAssignment_0 )
            // InternalLea.g:1478:3: rule__Iteration__IteratorAssignment_0
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
    // InternalLea.g:1486:1: rule__Iteration__Group__1 : rule__Iteration__Group__1__Impl rule__Iteration__Group__2 ;
    public final void rule__Iteration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1490:1: ( rule__Iteration__Group__1__Impl rule__Iteration__Group__2 )
            // InternalLea.g:1491:2: rule__Iteration__Group__1__Impl rule__Iteration__Group__2
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
    // InternalLea.g:1498:1: rule__Iteration__Group__1__Impl : ( ':' ) ;
    public final void rule__Iteration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1502:1: ( ( ':' ) )
            // InternalLea.g:1503:1: ( ':' )
            {
            // InternalLea.g:1503:1: ( ':' )
            // InternalLea.g:1504:2: ':'
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
    // InternalLea.g:1513:1: rule__Iteration__Group__2 : rule__Iteration__Group__2__Impl ;
    public final void rule__Iteration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1517:1: ( rule__Iteration__Group__2__Impl )
            // InternalLea.g:1518:2: rule__Iteration__Group__2__Impl
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
    // InternalLea.g:1524:1: rule__Iteration__Group__2__Impl : ( ( rule__Iteration__IterableAssignment_2 ) ) ;
    public final void rule__Iteration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1528:1: ( ( ( rule__Iteration__IterableAssignment_2 ) ) )
            // InternalLea.g:1529:1: ( ( rule__Iteration__IterableAssignment_2 ) )
            {
            // InternalLea.g:1529:1: ( ( rule__Iteration__IterableAssignment_2 ) )
            // InternalLea.g:1530:2: ( rule__Iteration__IterableAssignment_2 )
            {
             before(grammarAccess.getIterationAccess().getIterableAssignment_2()); 
            // InternalLea.g:1531:2: ( rule__Iteration__IterableAssignment_2 )
            // InternalLea.g:1531:3: rule__Iteration__IterableAssignment_2
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
    // InternalLea.g:1540:1: rule__Assignment__Group__0 : rule__Assignment__Group__0__Impl rule__Assignment__Group__1 ;
    public final void rule__Assignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1544:1: ( rule__Assignment__Group__0__Impl rule__Assignment__Group__1 )
            // InternalLea.g:1545:2: rule__Assignment__Group__0__Impl rule__Assignment__Group__1
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
    // InternalLea.g:1552:1: rule__Assignment__Group__0__Impl : ( '=' ) ;
    public final void rule__Assignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1556:1: ( ( '=' ) )
            // InternalLea.g:1557:1: ( '=' )
            {
            // InternalLea.g:1557:1: ( '=' )
            // InternalLea.g:1558:2: '='
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
    // InternalLea.g:1567:1: rule__Assignment__Group__1 : rule__Assignment__Group__1__Impl ;
    public final void rule__Assignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1571:1: ( rule__Assignment__Group__1__Impl )
            // InternalLea.g:1572:2: rule__Assignment__Group__1__Impl
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
    // InternalLea.g:1578:1: rule__Assignment__Group__1__Impl : ( ( rule__Assignment__Alternatives_1 ) ) ;
    public final void rule__Assignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1582:1: ( ( ( rule__Assignment__Alternatives_1 ) ) )
            // InternalLea.g:1583:1: ( ( rule__Assignment__Alternatives_1 ) )
            {
            // InternalLea.g:1583:1: ( ( rule__Assignment__Alternatives_1 ) )
            // InternalLea.g:1584:2: ( rule__Assignment__Alternatives_1 )
            {
             before(grammarAccess.getAssignmentAccess().getAlternatives_1()); 
            // InternalLea.g:1585:2: ( rule__Assignment__Alternatives_1 )
            // InternalLea.g:1585:3: rule__Assignment__Alternatives_1
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
    // InternalLea.g:1594:1: rule__Operation__Group__0 : rule__Operation__Group__0__Impl rule__Operation__Group__1 ;
    public final void rule__Operation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1598:1: ( rule__Operation__Group__0__Impl rule__Operation__Group__1 )
            // InternalLea.g:1599:2: rule__Operation__Group__0__Impl rule__Operation__Group__1
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
    // InternalLea.g:1606:1: rule__Operation__Group__0__Impl : ( ( rule__Operation__Group_0__0 )? ) ;
    public final void rule__Operation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1610:1: ( ( ( rule__Operation__Group_0__0 )? ) )
            // InternalLea.g:1611:1: ( ( rule__Operation__Group_0__0 )? )
            {
            // InternalLea.g:1611:1: ( ( rule__Operation__Group_0__0 )? )
            // InternalLea.g:1612:2: ( rule__Operation__Group_0__0 )?
            {
             before(grammarAccess.getOperationAccess().getGroup_0()); 
            // InternalLea.g:1613:2: ( rule__Operation__Group_0__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ID) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==24) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // InternalLea.g:1613:3: rule__Operation__Group_0__0
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
    // InternalLea.g:1621:1: rule__Operation__Group__1 : rule__Operation__Group__1__Impl rule__Operation__Group__2 ;
    public final void rule__Operation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1625:1: ( rule__Operation__Group__1__Impl rule__Operation__Group__2 )
            // InternalLea.g:1626:2: rule__Operation__Group__1__Impl rule__Operation__Group__2
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
    // InternalLea.g:1633:1: rule__Operation__Group__1__Impl : ( ( rule__Operation__CallAssignment_1 ) ) ;
    public final void rule__Operation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1637:1: ( ( ( rule__Operation__CallAssignment_1 ) ) )
            // InternalLea.g:1638:1: ( ( rule__Operation__CallAssignment_1 ) )
            {
            // InternalLea.g:1638:1: ( ( rule__Operation__CallAssignment_1 ) )
            // InternalLea.g:1639:2: ( rule__Operation__CallAssignment_1 )
            {
             before(grammarAccess.getOperationAccess().getCallAssignment_1()); 
            // InternalLea.g:1640:2: ( rule__Operation__CallAssignment_1 )
            // InternalLea.g:1640:3: rule__Operation__CallAssignment_1
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
    // InternalLea.g:1648:1: rule__Operation__Group__2 : rule__Operation__Group__2__Impl ;
    public final void rule__Operation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1652:1: ( rule__Operation__Group__2__Impl )
            // InternalLea.g:1653:2: rule__Operation__Group__2__Impl
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
    // InternalLea.g:1659:1: rule__Operation__Group__2__Impl : ( ( rule__Operation__Group_2__0 )? ) ;
    public final void rule__Operation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1663:1: ( ( ( rule__Operation__Group_2__0 )? ) )
            // InternalLea.g:1664:1: ( ( rule__Operation__Group_2__0 )? )
            {
            // InternalLea.g:1664:1: ( ( rule__Operation__Group_2__0 )? )
            // InternalLea.g:1665:2: ( rule__Operation__Group_2__0 )?
            {
             before(grammarAccess.getOperationAccess().getGroup_2()); 
            // InternalLea.g:1666:2: ( rule__Operation__Group_2__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==24) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalLea.g:1666:3: rule__Operation__Group_2__0
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
    // InternalLea.g:1675:1: rule__Operation__Group_0__0 : rule__Operation__Group_0__0__Impl rule__Operation__Group_0__1 ;
    public final void rule__Operation__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1679:1: ( rule__Operation__Group_0__0__Impl rule__Operation__Group_0__1 )
            // InternalLea.g:1680:2: rule__Operation__Group_0__0__Impl rule__Operation__Group_0__1
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
    // InternalLea.g:1687:1: rule__Operation__Group_0__0__Impl : ( ( rule__Operation__ElementAssignment_0_0 ) ) ;
    public final void rule__Operation__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1691:1: ( ( ( rule__Operation__ElementAssignment_0_0 ) ) )
            // InternalLea.g:1692:1: ( ( rule__Operation__ElementAssignment_0_0 ) )
            {
            // InternalLea.g:1692:1: ( ( rule__Operation__ElementAssignment_0_0 ) )
            // InternalLea.g:1693:2: ( rule__Operation__ElementAssignment_0_0 )
            {
             before(grammarAccess.getOperationAccess().getElementAssignment_0_0()); 
            // InternalLea.g:1694:2: ( rule__Operation__ElementAssignment_0_0 )
            // InternalLea.g:1694:3: rule__Operation__ElementAssignment_0_0
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
    // InternalLea.g:1702:1: rule__Operation__Group_0__1 : rule__Operation__Group_0__1__Impl ;
    public final void rule__Operation__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1706:1: ( rule__Operation__Group_0__1__Impl )
            // InternalLea.g:1707:2: rule__Operation__Group_0__1__Impl
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
    // InternalLea.g:1713:1: rule__Operation__Group_0__1__Impl : ( '.' ) ;
    public final void rule__Operation__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1717:1: ( ( '.' ) )
            // InternalLea.g:1718:1: ( '.' )
            {
            // InternalLea.g:1718:1: ( '.' )
            // InternalLea.g:1719:2: '.'
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
    // InternalLea.g:1729:1: rule__Operation__Group_2__0 : rule__Operation__Group_2__0__Impl rule__Operation__Group_2__1 ;
    public final void rule__Operation__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1733:1: ( rule__Operation__Group_2__0__Impl rule__Operation__Group_2__1 )
            // InternalLea.g:1734:2: rule__Operation__Group_2__0__Impl rule__Operation__Group_2__1
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
    // InternalLea.g:1741:1: rule__Operation__Group_2__0__Impl : ( '.' ) ;
    public final void rule__Operation__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1745:1: ( ( '.' ) )
            // InternalLea.g:1746:1: ( '.' )
            {
            // InternalLea.g:1746:1: ( '.' )
            // InternalLea.g:1747:2: '.'
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
    // InternalLea.g:1756:1: rule__Operation__Group_2__1 : rule__Operation__Group_2__1__Impl ;
    public final void rule__Operation__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1760:1: ( rule__Operation__Group_2__1__Impl )
            // InternalLea.g:1761:2: rule__Operation__Group_2__1__Impl
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
    // InternalLea.g:1767:1: rule__Operation__Group_2__1__Impl : ( ( rule__Operation__CallAssignment_2_1 ) ) ;
    public final void rule__Operation__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1771:1: ( ( ( rule__Operation__CallAssignment_2_1 ) ) )
            // InternalLea.g:1772:1: ( ( rule__Operation__CallAssignment_2_1 ) )
            {
            // InternalLea.g:1772:1: ( ( rule__Operation__CallAssignment_2_1 ) )
            // InternalLea.g:1773:2: ( rule__Operation__CallAssignment_2_1 )
            {
             before(grammarAccess.getOperationAccess().getCallAssignment_2_1()); 
            // InternalLea.g:1774:2: ( rule__Operation__CallAssignment_2_1 )
            // InternalLea.g:1774:3: rule__Operation__CallAssignment_2_1
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
    // InternalLea.g:1783:1: rule__Call__Group__0 : rule__Call__Group__0__Impl rule__Call__Group__1 ;
    public final void rule__Call__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1787:1: ( rule__Call__Group__0__Impl rule__Call__Group__1 )
            // InternalLea.g:1788:2: rule__Call__Group__0__Impl rule__Call__Group__1
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
    // InternalLea.g:1795:1: rule__Call__Group__0__Impl : ( ( rule__Call__NameAssignment_0 ) ) ;
    public final void rule__Call__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1799:1: ( ( ( rule__Call__NameAssignment_0 ) ) )
            // InternalLea.g:1800:1: ( ( rule__Call__NameAssignment_0 ) )
            {
            // InternalLea.g:1800:1: ( ( rule__Call__NameAssignment_0 ) )
            // InternalLea.g:1801:2: ( rule__Call__NameAssignment_0 )
            {
             before(grammarAccess.getCallAccess().getNameAssignment_0()); 
            // InternalLea.g:1802:2: ( rule__Call__NameAssignment_0 )
            // InternalLea.g:1802:3: rule__Call__NameAssignment_0
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
    // InternalLea.g:1810:1: rule__Call__Group__1 : rule__Call__Group__1__Impl rule__Call__Group__2 ;
    public final void rule__Call__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1814:1: ( rule__Call__Group__1__Impl rule__Call__Group__2 )
            // InternalLea.g:1815:2: rule__Call__Group__1__Impl rule__Call__Group__2
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
    // InternalLea.g:1822:1: rule__Call__Group__1__Impl : ( '(' ) ;
    public final void rule__Call__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1826:1: ( ( '(' ) )
            // InternalLea.g:1827:1: ( '(' )
            {
            // InternalLea.g:1827:1: ( '(' )
            // InternalLea.g:1828:2: '('
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
    // InternalLea.g:1837:1: rule__Call__Group__2 : rule__Call__Group__2__Impl rule__Call__Group__3 ;
    public final void rule__Call__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1841:1: ( rule__Call__Group__2__Impl rule__Call__Group__3 )
            // InternalLea.g:1842:2: rule__Call__Group__2__Impl rule__Call__Group__3
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
    // InternalLea.g:1849:1: rule__Call__Group__2__Impl : ( ( rule__Call__ParametersAssignment_2 )? ) ;
    public final void rule__Call__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1853:1: ( ( ( rule__Call__ParametersAssignment_2 )? ) )
            // InternalLea.g:1854:1: ( ( rule__Call__ParametersAssignment_2 )? )
            {
            // InternalLea.g:1854:1: ( ( rule__Call__ParametersAssignment_2 )? )
            // InternalLea.g:1855:2: ( rule__Call__ParametersAssignment_2 )?
            {
             before(grammarAccess.getCallAccess().getParametersAssignment_2()); 
            // InternalLea.g:1856:2: ( rule__Call__ParametersAssignment_2 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_ID && LA19_0<=RULE_STRING)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalLea.g:1856:3: rule__Call__ParametersAssignment_2
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
    // InternalLea.g:1864:1: rule__Call__Group__3 : rule__Call__Group__3__Impl ;
    public final void rule__Call__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1868:1: ( rule__Call__Group__3__Impl )
            // InternalLea.g:1869:2: rule__Call__Group__3__Impl
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
    // InternalLea.g:1875:1: rule__Call__Group__3__Impl : ( ')' ) ;
    public final void rule__Call__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1879:1: ( ( ')' ) )
            // InternalLea.g:1880:1: ( ')' )
            {
            // InternalLea.g:1880:1: ( ')' )
            // InternalLea.g:1881:2: ')'
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
    // InternalLea.g:1891:1: rule__ParameterList__Group__0 : rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 ;
    public final void rule__ParameterList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1895:1: ( rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 )
            // InternalLea.g:1896:2: rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1
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
    // InternalLea.g:1903:1: rule__ParameterList__Group__0__Impl : ( ( rule__ParameterList__ParameterListAssignment_0 ) ) ;
    public final void rule__ParameterList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1907:1: ( ( ( rule__ParameterList__ParameterListAssignment_0 ) ) )
            // InternalLea.g:1908:1: ( ( rule__ParameterList__ParameterListAssignment_0 ) )
            {
            // InternalLea.g:1908:1: ( ( rule__ParameterList__ParameterListAssignment_0 ) )
            // InternalLea.g:1909:2: ( rule__ParameterList__ParameterListAssignment_0 )
            {
             before(grammarAccess.getParameterListAccess().getParameterListAssignment_0()); 
            // InternalLea.g:1910:2: ( rule__ParameterList__ParameterListAssignment_0 )
            // InternalLea.g:1910:3: rule__ParameterList__ParameterListAssignment_0
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
    // InternalLea.g:1918:1: rule__ParameterList__Group__1 : rule__ParameterList__Group__1__Impl ;
    public final void rule__ParameterList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1922:1: ( rule__ParameterList__Group__1__Impl )
            // InternalLea.g:1923:2: rule__ParameterList__Group__1__Impl
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
    // InternalLea.g:1929:1: rule__ParameterList__Group__1__Impl : ( ( rule__ParameterList__Group_1__0 )* ) ;
    public final void rule__ParameterList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1933:1: ( ( ( rule__ParameterList__Group_1__0 )* ) )
            // InternalLea.g:1934:1: ( ( rule__ParameterList__Group_1__0 )* )
            {
            // InternalLea.g:1934:1: ( ( rule__ParameterList__Group_1__0 )* )
            // InternalLea.g:1935:2: ( rule__ParameterList__Group_1__0 )*
            {
             before(grammarAccess.getParameterListAccess().getGroup_1()); 
            // InternalLea.g:1936:2: ( rule__ParameterList__Group_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==14) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalLea.g:1936:3: rule__ParameterList__Group_1__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__ParameterList__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // InternalLea.g:1945:1: rule__ParameterList__Group_1__0 : rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 ;
    public final void rule__ParameterList__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1949:1: ( rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 )
            // InternalLea.g:1950:2: rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1
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
    // InternalLea.g:1957:1: rule__ParameterList__Group_1__0__Impl : ( ',' ) ;
    public final void rule__ParameterList__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1961:1: ( ( ',' ) )
            // InternalLea.g:1962:1: ( ',' )
            {
            // InternalLea.g:1962:1: ( ',' )
            // InternalLea.g:1963:2: ','
            {
             before(grammarAccess.getParameterListAccess().getCommaKeyword_1_0()); 
            match(input,14,FOLLOW_2); 
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
    // InternalLea.g:1972:1: rule__ParameterList__Group_1__1 : rule__ParameterList__Group_1__1__Impl ;
    public final void rule__ParameterList__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1976:1: ( rule__ParameterList__Group_1__1__Impl )
            // InternalLea.g:1977:2: rule__ParameterList__Group_1__1__Impl
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
    // InternalLea.g:1983:1: rule__ParameterList__Group_1__1__Impl : ( ( rule__ParameterList__ParameterListAssignment_1_1 ) ) ;
    public final void rule__ParameterList__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:1987:1: ( ( ( rule__ParameterList__ParameterListAssignment_1_1 ) ) )
            // InternalLea.g:1988:1: ( ( rule__ParameterList__ParameterListAssignment_1_1 ) )
            {
            // InternalLea.g:1988:1: ( ( rule__ParameterList__ParameterListAssignment_1_1 ) )
            // InternalLea.g:1989:2: ( rule__ParameterList__ParameterListAssignment_1_1 )
            {
             before(grammarAccess.getParameterListAccess().getParameterListAssignment_1_1()); 
            // InternalLea.g:1990:2: ( rule__ParameterList__ParameterListAssignment_1_1 )
            // InternalLea.g:1990:3: rule__ParameterList__ParameterListAssignment_1_1
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


    // $ANTLR start "rule__AnalysisDefinition__ElementsAssignment_1_0"
    // InternalLea.g:1999:1: rule__AnalysisDefinition__ElementsAssignment_1_0 : ( ruleArtifactDeclaration ) ;
    public final void rule__AnalysisDefinition__ElementsAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2003:1: ( ( ruleArtifactDeclaration ) )
            // InternalLea.g:2004:2: ( ruleArtifactDeclaration )
            {
            // InternalLea.g:2004:2: ( ruleArtifactDeclaration )
            // InternalLea.g:2005:3: ruleArtifactDeclaration
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getElementsArtifactDeclarationParserRuleCall_1_0_0()); 
            pushFollow(FOLLOW_2);
            ruleArtifactDeclaration();

            state._fsp--;

             after(grammarAccess.getAnalysisDefinitionAccess().getElementsArtifactDeclarationParserRuleCall_1_0_0()); 

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
    // $ANTLR end "rule__AnalysisDefinition__ElementsAssignment_1_0"


    // $ANTLR start "rule__AnalysisDefinition__ElementsAssignment_1_1"
    // InternalLea.g:2014:1: rule__AnalysisDefinition__ElementsAssignment_1_1 : ( ruleFragmentDeclaration ) ;
    public final void rule__AnalysisDefinition__ElementsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2018:1: ( ( ruleFragmentDeclaration ) )
            // InternalLea.g:2019:2: ( ruleFragmentDeclaration )
            {
            // InternalLea.g:2019:2: ( ruleFragmentDeclaration )
            // InternalLea.g:2020:3: ruleFragmentDeclaration
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getElementsFragmentDeclarationParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleFragmentDeclaration();

            state._fsp--;

             after(grammarAccess.getAnalysisDefinitionAccess().getElementsFragmentDeclarationParserRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__AnalysisDefinition__ElementsAssignment_1_1"


    // $ANTLR start "rule__AnalysisDefinition__ElementsAssignment_1_2"
    // InternalLea.g:2029:1: rule__AnalysisDefinition__ElementsAssignment_1_2 : ( ruleResultDeclaration ) ;
    public final void rule__AnalysisDefinition__ElementsAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2033:1: ( ( ruleResultDeclaration ) )
            // InternalLea.g:2034:2: ( ruleResultDeclaration )
            {
            // InternalLea.g:2034:2: ( ruleResultDeclaration )
            // InternalLea.g:2035:3: ruleResultDeclaration
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getElementsResultDeclarationParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleResultDeclaration();

            state._fsp--;

             after(grammarAccess.getAnalysisDefinitionAccess().getElementsResultDeclarationParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__AnalysisDefinition__ElementsAssignment_1_2"


    // $ANTLR start "rule__AnalysisDefinition__ElementsAssignment_1_3"
    // InternalLea.g:2044:1: rule__AnalysisDefinition__ElementsAssignment_1_3 : ( ruleChangeIdentifierAssignment ) ;
    public final void rule__AnalysisDefinition__ElementsAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2048:1: ( ( ruleChangeIdentifierAssignment ) )
            // InternalLea.g:2049:2: ( ruleChangeIdentifierAssignment )
            {
            // InternalLea.g:2049:2: ( ruleChangeIdentifierAssignment )
            // InternalLea.g:2050:3: ruleChangeIdentifierAssignment
            {
             before(grammarAccess.getAnalysisDefinitionAccess().getElementsChangeIdentifierAssignmentParserRuleCall_1_3_0()); 
            pushFollow(FOLLOW_2);
            ruleChangeIdentifierAssignment();

            state._fsp--;

             after(grammarAccess.getAnalysisDefinitionAccess().getElementsChangeIdentifierAssignmentParserRuleCall_1_3_0()); 

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
    // $ANTLR end "rule__AnalysisDefinition__ElementsAssignment_1_3"


    // $ANTLR start "rule__ChangeIdentifierAssignment__IdentifierAssignment_1"
    // InternalLea.g:2059:1: rule__ChangeIdentifierAssignment__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ChangeIdentifierAssignment__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2063:1: ( ( RULE_ID ) )
            // InternalLea.g:2064:2: ( RULE_ID )
            {
            // InternalLea.g:2064:2: ( RULE_ID )
            // InternalLea.g:2065:3: RULE_ID
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
    // InternalLea.g:2074:1: rule__ChangeIdentifierAssignment__ElementsAssignment_3 : ( RULE_ID ) ;
    public final void rule__ChangeIdentifierAssignment__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2078:1: ( ( RULE_ID ) )
            // InternalLea.g:2079:2: ( RULE_ID )
            {
            // InternalLea.g:2079:2: ( RULE_ID )
            // InternalLea.g:2080:3: RULE_ID
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
    // InternalLea.g:2089:1: rule__ChangeIdentifierAssignment__ElementsAssignment_4_1 : ( RULE_ID ) ;
    public final void rule__ChangeIdentifierAssignment__ElementsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2093:1: ( ( RULE_ID ) )
            // InternalLea.g:2094:2: ( RULE_ID )
            {
            // InternalLea.g:2094:2: ( RULE_ID )
            // InternalLea.g:2095:3: RULE_ID
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


    // $ANTLR start "rule__ArtifactDeclaration__TypeAssignment_2"
    // InternalLea.g:2104:1: rule__ArtifactDeclaration__TypeAssignment_2 : ( RULE_ID ) ;
    public final void rule__ArtifactDeclaration__TypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2108:1: ( ( RULE_ID ) )
            // InternalLea.g:2109:2: ( RULE_ID )
            {
            // InternalLea.g:2109:2: ( RULE_ID )
            // InternalLea.g:2110:3: RULE_ID
            {
             before(grammarAccess.getArtifactDeclarationAccess().getTypeIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getArtifactDeclarationAccess().getTypeIDTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__TypeAssignment_2"


    // $ANTLR start "rule__ArtifactDeclaration__SetAssignment_4"
    // InternalLea.g:2119:1: rule__ArtifactDeclaration__SetAssignment_4 : ( ruleSetDefinition ) ;
    public final void rule__ArtifactDeclaration__SetAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2123:1: ( ( ruleSetDefinition ) )
            // InternalLea.g:2124:2: ( ruleSetDefinition )
            {
            // InternalLea.g:2124:2: ( ruleSetDefinition )
            // InternalLea.g:2125:3: ruleSetDefinition
            {
             before(grammarAccess.getArtifactDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleSetDefinition();

            state._fsp--;

             after(grammarAccess.getArtifactDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__SetAssignment_4"


    // $ANTLR start "rule__ArtifactDeclaration__NameAssignment_5"
    // InternalLea.g:2134:1: rule__ArtifactDeclaration__NameAssignment_5 : ( RULE_ID ) ;
    public final void rule__ArtifactDeclaration__NameAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2138:1: ( ( RULE_ID ) )
            // InternalLea.g:2139:2: ( RULE_ID )
            {
            // InternalLea.g:2139:2: ( RULE_ID )
            // InternalLea.g:2140:3: RULE_ID
            {
             before(grammarAccess.getArtifactDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getArtifactDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__NameAssignment_5"


    // $ANTLR start "rule__ArtifactDeclaration__InitializationAssignment_6"
    // InternalLea.g:2149:1: rule__ArtifactDeclaration__InitializationAssignment_6 : ( ruleAssignment ) ;
    public final void rule__ArtifactDeclaration__InitializationAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2153:1: ( ( ruleAssignment ) )
            // InternalLea.g:2154:2: ( ruleAssignment )
            {
            // InternalLea.g:2154:2: ( ruleAssignment )
            // InternalLea.g:2155:3: ruleAssignment
            {
             before(grammarAccess.getArtifactDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getArtifactDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__ArtifactDeclaration__InitializationAssignment_6"


    // $ANTLR start "rule__FragmentDeclaration__TypeAssignment_2"
    // InternalLea.g:2164:1: rule__FragmentDeclaration__TypeAssignment_2 : ( RULE_ID ) ;
    public final void rule__FragmentDeclaration__TypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2168:1: ( ( RULE_ID ) )
            // InternalLea.g:2169:2: ( RULE_ID )
            {
            // InternalLea.g:2169:2: ( RULE_ID )
            // InternalLea.g:2170:3: RULE_ID
            {
             before(grammarAccess.getFragmentDeclarationAccess().getTypeIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFragmentDeclarationAccess().getTypeIDTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__FragmentDeclaration__TypeAssignment_2"


    // $ANTLR start "rule__FragmentDeclaration__SetAssignment_4"
    // InternalLea.g:2179:1: rule__FragmentDeclaration__SetAssignment_4 : ( ruleSetDefinition ) ;
    public final void rule__FragmentDeclaration__SetAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2183:1: ( ( ruleSetDefinition ) )
            // InternalLea.g:2184:2: ( ruleSetDefinition )
            {
            // InternalLea.g:2184:2: ( ruleSetDefinition )
            // InternalLea.g:2185:3: ruleSetDefinition
            {
             before(grammarAccess.getFragmentDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleSetDefinition();

            state._fsp--;

             after(grammarAccess.getFragmentDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__FragmentDeclaration__SetAssignment_4"


    // $ANTLR start "rule__FragmentDeclaration__NameAssignment_5"
    // InternalLea.g:2194:1: rule__FragmentDeclaration__NameAssignment_5 : ( RULE_ID ) ;
    public final void rule__FragmentDeclaration__NameAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2198:1: ( ( RULE_ID ) )
            // InternalLea.g:2199:2: ( RULE_ID )
            {
            // InternalLea.g:2199:2: ( RULE_ID )
            // InternalLea.g:2200:3: RULE_ID
            {
             before(grammarAccess.getFragmentDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getFragmentDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 

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
    // $ANTLR end "rule__FragmentDeclaration__NameAssignment_5"


    // $ANTLR start "rule__FragmentDeclaration__InitializationAssignment_6"
    // InternalLea.g:2209:1: rule__FragmentDeclaration__InitializationAssignment_6 : ( ruleAssignment ) ;
    public final void rule__FragmentDeclaration__InitializationAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2213:1: ( ( ruleAssignment ) )
            // InternalLea.g:2214:2: ( ruleAssignment )
            {
            // InternalLea.g:2214:2: ( ruleAssignment )
            // InternalLea.g:2215:3: ruleAssignment
            {
             before(grammarAccess.getFragmentDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getFragmentDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__FragmentDeclaration__InitializationAssignment_6"


    // $ANTLR start "rule__ResultDeclaration__TypeAssignment_2"
    // InternalLea.g:2224:1: rule__ResultDeclaration__TypeAssignment_2 : ( RULE_ID ) ;
    public final void rule__ResultDeclaration__TypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2228:1: ( ( RULE_ID ) )
            // InternalLea.g:2229:2: ( RULE_ID )
            {
            // InternalLea.g:2229:2: ( RULE_ID )
            // InternalLea.g:2230:3: RULE_ID
            {
             before(grammarAccess.getResultDeclarationAccess().getTypeIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResultDeclarationAccess().getTypeIDTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__ResultDeclaration__TypeAssignment_2"


    // $ANTLR start "rule__ResultDeclaration__SetAssignment_4"
    // InternalLea.g:2239:1: rule__ResultDeclaration__SetAssignment_4 : ( ruleSetDefinition ) ;
    public final void rule__ResultDeclaration__SetAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2243:1: ( ( ruleSetDefinition ) )
            // InternalLea.g:2244:2: ( ruleSetDefinition )
            {
            // InternalLea.g:2244:2: ( ruleSetDefinition )
            // InternalLea.g:2245:3: ruleSetDefinition
            {
             before(grammarAccess.getResultDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleSetDefinition();

            state._fsp--;

             after(grammarAccess.getResultDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__ResultDeclaration__SetAssignment_4"


    // $ANTLR start "rule__ResultDeclaration__NameAssignment_5"
    // InternalLea.g:2254:1: rule__ResultDeclaration__NameAssignment_5 : ( RULE_ID ) ;
    public final void rule__ResultDeclaration__NameAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2258:1: ( ( RULE_ID ) )
            // InternalLea.g:2259:2: ( RULE_ID )
            {
            // InternalLea.g:2259:2: ( RULE_ID )
            // InternalLea.g:2260:3: RULE_ID
            {
             before(grammarAccess.getResultDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResultDeclarationAccess().getNameIDTerminalRuleCall_5_0()); 

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
    // $ANTLR end "rule__ResultDeclaration__NameAssignment_5"


    // $ANTLR start "rule__ResultDeclaration__InitializationAssignment_6"
    // InternalLea.g:2269:1: rule__ResultDeclaration__InitializationAssignment_6 : ( ruleAssignment ) ;
    public final void rule__ResultDeclaration__InitializationAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2273:1: ( ( ruleAssignment ) )
            // InternalLea.g:2274:2: ( ruleAssignment )
            {
            // InternalLea.g:2274:2: ( ruleAssignment )
            // InternalLea.g:2275:3: ruleAssignment
            {
             before(grammarAccess.getResultDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getResultDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__ResultDeclaration__InitializationAssignment_6"


    // $ANTLR start "rule__SetDefinition__IterationAssignment_2"
    // InternalLea.g:2284:1: rule__SetDefinition__IterationAssignment_2 : ( ruleIteration ) ;
    public final void rule__SetDefinition__IterationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2288:1: ( ( ruleIteration ) )
            // InternalLea.g:2289:2: ( ruleIteration )
            {
            // InternalLea.g:2289:2: ( ruleIteration )
            // InternalLea.g:2290:3: ruleIteration
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
    // InternalLea.g:2299:1: rule__Iteration__IteratorAssignment_0 : ( RULE_ID ) ;
    public final void rule__Iteration__IteratorAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2303:1: ( ( RULE_ID ) )
            // InternalLea.g:2304:2: ( RULE_ID )
            {
            // InternalLea.g:2304:2: ( RULE_ID )
            // InternalLea.g:2305:3: RULE_ID
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
    // InternalLea.g:2314:1: rule__Iteration__IterableAssignment_2 : ( RULE_ID ) ;
    public final void rule__Iteration__IterableAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2318:1: ( ( RULE_ID ) )
            // InternalLea.g:2319:2: ( RULE_ID )
            {
            // InternalLea.g:2319:2: ( RULE_ID )
            // InternalLea.g:2320:3: RULE_ID
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
    // InternalLea.g:2329:1: rule__Assignment__ElementAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__Assignment__ElementAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2333:1: ( ( RULE_ID ) )
            // InternalLea.g:2334:2: ( RULE_ID )
            {
            // InternalLea.g:2334:2: ( RULE_ID )
            // InternalLea.g:2335:3: RULE_ID
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
    // InternalLea.g:2344:1: rule__Assignment__OperationAssignment_1_1 : ( ruleOperation ) ;
    public final void rule__Assignment__OperationAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2348:1: ( ( ruleOperation ) )
            // InternalLea.g:2349:2: ( ruleOperation )
            {
            // InternalLea.g:2349:2: ( ruleOperation )
            // InternalLea.g:2350:3: ruleOperation
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
    // InternalLea.g:2359:1: rule__Operation__ElementAssignment_0_0 : ( RULE_ID ) ;
    public final void rule__Operation__ElementAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2363:1: ( ( RULE_ID ) )
            // InternalLea.g:2364:2: ( RULE_ID )
            {
            // InternalLea.g:2364:2: ( RULE_ID )
            // InternalLea.g:2365:3: RULE_ID
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
    // InternalLea.g:2374:1: rule__Operation__CallAssignment_1 : ( ruleCall ) ;
    public final void rule__Operation__CallAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2378:1: ( ( ruleCall ) )
            // InternalLea.g:2379:2: ( ruleCall )
            {
            // InternalLea.g:2379:2: ( ruleCall )
            // InternalLea.g:2380:3: ruleCall
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
    // InternalLea.g:2389:1: rule__Operation__CallAssignment_2_1 : ( ruleCall ) ;
    public final void rule__Operation__CallAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2393:1: ( ( ruleCall ) )
            // InternalLea.g:2394:2: ( ruleCall )
            {
            // InternalLea.g:2394:2: ( ruleCall )
            // InternalLea.g:2395:3: ruleCall
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
    // InternalLea.g:2404:1: rule__Call__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Call__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2408:1: ( ( RULE_ID ) )
            // InternalLea.g:2409:2: ( RULE_ID )
            {
            // InternalLea.g:2409:2: ( RULE_ID )
            // InternalLea.g:2410:3: RULE_ID
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
    // InternalLea.g:2419:1: rule__Call__ParametersAssignment_2 : ( ruleParameterList ) ;
    public final void rule__Call__ParametersAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2423:1: ( ( ruleParameterList ) )
            // InternalLea.g:2424:2: ( ruleParameterList )
            {
            // InternalLea.g:2424:2: ( ruleParameterList )
            // InternalLea.g:2425:3: ruleParameterList
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
    // InternalLea.g:2434:1: rule__ParameterList__ParameterListAssignment_0 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParameterListAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2438:1: ( ( ruleParameter ) )
            // InternalLea.g:2439:2: ( ruleParameter )
            {
            // InternalLea.g:2439:2: ( ruleParameter )
            // InternalLea.g:2440:3: ruleParameter
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
    // InternalLea.g:2449:1: rule__ParameterList__ParameterListAssignment_1_1 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParameterListAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2453:1: ( ( ruleParameter ) )
            // InternalLea.g:2454:2: ( ruleParameter )
            {
            // InternalLea.g:2454:2: ( ruleParameter )
            // InternalLea.g:2455:3: ruleParameter
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
    // InternalLea.g:2464:1: rule__Parameter__TextAssignment_0 : ( RULE_STRING ) ;
    public final void rule__Parameter__TextAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2468:1: ( ( RULE_STRING ) )
            // InternalLea.g:2469:2: ( RULE_STRING )
            {
            // InternalLea.g:2469:2: ( RULE_STRING )
            // InternalLea.g:2470:3: RULE_STRING
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
    // InternalLea.g:2479:1: rule__Parameter__ElementAssignment_1 : ( RULE_ID ) ;
    public final void rule__Parameter__ElementAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2483:1: ( ( RULE_ID ) )
            // InternalLea.g:2484:2: ( RULE_ID )
            {
            // InternalLea.g:2484:2: ( RULE_ID )
            // InternalLea.g:2485:3: RULE_ID
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
    // InternalLea.g:2494:1: rule__Parameter__OperationAssignment_2 : ( ruleOperation ) ;
    public final void rule__Parameter__OperationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLea.g:2498:1: ( ( ruleOperation ) )
            // InternalLea.g:2499:2: ( ruleOperation )
            {
            // InternalLea.g:2499:2: ( ruleOperation )
            // InternalLea.g:2500:3: ruleOperation
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
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000000000C8800L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000000C8802L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000100010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000802000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000004000030L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000000030L});

}