package net.ssehub.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import net.ssehub.services.LeaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalLeaParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'assign'", "'to'", "','", "';'", "'Artifact'", "'<'", "'>'", "'Fragment'", "'Result'", "'['", "']'", "':'", "'='", "'.'", "'('", "')'"
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

        public InternalLeaParser(TokenStream input, LeaGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "AnalysisDefinition";
       	}

       	@Override
       	protected LeaGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleAnalysisDefinition"
    // InternalLea.g:64:1: entryRuleAnalysisDefinition returns [EObject current=null] : iv_ruleAnalysisDefinition= ruleAnalysisDefinition EOF ;
    public final EObject entryRuleAnalysisDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysisDefinition = null;


        try {
            // InternalLea.g:64:59: (iv_ruleAnalysisDefinition= ruleAnalysisDefinition EOF )
            // InternalLea.g:65:2: iv_ruleAnalysisDefinition= ruleAnalysisDefinition EOF
            {
             newCompositeNode(grammarAccess.getAnalysisDefinitionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnalysisDefinition=ruleAnalysisDefinition();

            state._fsp--;

             current =iv_ruleAnalysisDefinition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnalysisDefinition"


    // $ANTLR start "ruleAnalysisDefinition"
    // InternalLea.g:71:1: ruleAnalysisDefinition returns [EObject current=null] : ( () ( ( (lv_elements_1_0= ruleArtifactDeclaration ) ) | ( (lv_elements_2_0= ruleFragmentDeclaration ) ) | ( (lv_elements_3_0= ruleResultDeclaration ) ) | ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) ) )* ) ;
    public final EObject ruleAnalysisDefinition() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_1_0 = null;

        EObject lv_elements_2_0 = null;

        EObject lv_elements_3_0 = null;

        EObject lv_elements_4_0 = null;



        	enterRule();

        try {
            // InternalLea.g:77:2: ( ( () ( ( (lv_elements_1_0= ruleArtifactDeclaration ) ) | ( (lv_elements_2_0= ruleFragmentDeclaration ) ) | ( (lv_elements_3_0= ruleResultDeclaration ) ) | ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) ) )* ) )
            // InternalLea.g:78:2: ( () ( ( (lv_elements_1_0= ruleArtifactDeclaration ) ) | ( (lv_elements_2_0= ruleFragmentDeclaration ) ) | ( (lv_elements_3_0= ruleResultDeclaration ) ) | ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) ) )* )
            {
            // InternalLea.g:78:2: ( () ( ( (lv_elements_1_0= ruleArtifactDeclaration ) ) | ( (lv_elements_2_0= ruleFragmentDeclaration ) ) | ( (lv_elements_3_0= ruleResultDeclaration ) ) | ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) ) )* )
            // InternalLea.g:79:3: () ( ( (lv_elements_1_0= ruleArtifactDeclaration ) ) | ( (lv_elements_2_0= ruleFragmentDeclaration ) ) | ( (lv_elements_3_0= ruleResultDeclaration ) ) | ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) ) )*
            {
            // InternalLea.g:79:3: ()
            // InternalLea.g:80:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAnalysisDefinitionAccess().getAnalysisDefinitionAction_0(),
            					current);
            			

            }

            // InternalLea.g:86:3: ( ( (lv_elements_1_0= ruleArtifactDeclaration ) ) | ( (lv_elements_2_0= ruleFragmentDeclaration ) ) | ( (lv_elements_3_0= ruleResultDeclaration ) ) | ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) ) )*
            loop1:
            do {
                int alt1=5;
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

                }

                switch (alt1) {
            	case 1 :
            	    // InternalLea.g:87:4: ( (lv_elements_1_0= ruleArtifactDeclaration ) )
            	    {
            	    // InternalLea.g:87:4: ( (lv_elements_1_0= ruleArtifactDeclaration ) )
            	    // InternalLea.g:88:5: (lv_elements_1_0= ruleArtifactDeclaration )
            	    {
            	    // InternalLea.g:88:5: (lv_elements_1_0= ruleArtifactDeclaration )
            	    // InternalLea.g:89:6: lv_elements_1_0= ruleArtifactDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getAnalysisDefinitionAccess().getElementsArtifactDeclarationParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_elements_1_0=ruleArtifactDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAnalysisDefinitionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_1_0,
            	    							"net.ssehub.Lea.ArtifactDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalLea.g:107:4: ( (lv_elements_2_0= ruleFragmentDeclaration ) )
            	    {
            	    // InternalLea.g:107:4: ( (lv_elements_2_0= ruleFragmentDeclaration ) )
            	    // InternalLea.g:108:5: (lv_elements_2_0= ruleFragmentDeclaration )
            	    {
            	    // InternalLea.g:108:5: (lv_elements_2_0= ruleFragmentDeclaration )
            	    // InternalLea.g:109:6: lv_elements_2_0= ruleFragmentDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getAnalysisDefinitionAccess().getElementsFragmentDeclarationParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_elements_2_0=ruleFragmentDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAnalysisDefinitionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_2_0,
            	    							"net.ssehub.Lea.FragmentDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalLea.g:127:4: ( (lv_elements_3_0= ruleResultDeclaration ) )
            	    {
            	    // InternalLea.g:127:4: ( (lv_elements_3_0= ruleResultDeclaration ) )
            	    // InternalLea.g:128:5: (lv_elements_3_0= ruleResultDeclaration )
            	    {
            	    // InternalLea.g:128:5: (lv_elements_3_0= ruleResultDeclaration )
            	    // InternalLea.g:129:6: lv_elements_3_0= ruleResultDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getAnalysisDefinitionAccess().getElementsResultDeclarationParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_elements_3_0=ruleResultDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAnalysisDefinitionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_3_0,
            	    							"net.ssehub.Lea.ResultDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalLea.g:147:4: ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) )
            	    {
            	    // InternalLea.g:147:4: ( (lv_elements_4_0= ruleChangeIdentifierAssignment ) )
            	    // InternalLea.g:148:5: (lv_elements_4_0= ruleChangeIdentifierAssignment )
            	    {
            	    // InternalLea.g:148:5: (lv_elements_4_0= ruleChangeIdentifierAssignment )
            	    // InternalLea.g:149:6: lv_elements_4_0= ruleChangeIdentifierAssignment
            	    {

            	    						newCompositeNode(grammarAccess.getAnalysisDefinitionAccess().getElementsChangeIdentifierAssignmentParserRuleCall_1_3_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_elements_4_0=ruleChangeIdentifierAssignment();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAnalysisDefinitionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_4_0,
            	    							"net.ssehub.Lea.ChangeIdentifierAssignment");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnalysisDefinition"


    // $ANTLR start "entryRuleChangeIdentifierAssignment"
    // InternalLea.g:171:1: entryRuleChangeIdentifierAssignment returns [EObject current=null] : iv_ruleChangeIdentifierAssignment= ruleChangeIdentifierAssignment EOF ;
    public final EObject entryRuleChangeIdentifierAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChangeIdentifierAssignment = null;


        try {
            // InternalLea.g:171:67: (iv_ruleChangeIdentifierAssignment= ruleChangeIdentifierAssignment EOF )
            // InternalLea.g:172:2: iv_ruleChangeIdentifierAssignment= ruleChangeIdentifierAssignment EOF
            {
             newCompositeNode(grammarAccess.getChangeIdentifierAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleChangeIdentifierAssignment=ruleChangeIdentifierAssignment();

            state._fsp--;

             current =iv_ruleChangeIdentifierAssignment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleChangeIdentifierAssignment"


    // $ANTLR start "ruleChangeIdentifierAssignment"
    // InternalLea.g:178:1: ruleChangeIdentifierAssignment returns [EObject current=null] : (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? ) ;
    public final EObject ruleChangeIdentifierAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token lv_elements_3_0=null;
        Token otherlv_4=null;
        Token lv_elements_5_0=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalLea.g:184:2: ( (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? ) )
            // InternalLea.g:185:2: (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? )
            {
            // InternalLea.g:185:2: (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? )
            // InternalLea.g:186:3: otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )?
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getChangeIdentifierAssignmentAccess().getAssignKeyword_0());
            		
            // InternalLea.g:190:3: ( (lv_identifier_1_0= RULE_ID ) )
            // InternalLea.g:191:4: (lv_identifier_1_0= RULE_ID )
            {
            // InternalLea.g:191:4: (lv_identifier_1_0= RULE_ID )
            // InternalLea.g:192:5: lv_identifier_1_0= RULE_ID
            {
            lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_identifier_1_0, grammarAccess.getChangeIdentifierAssignmentAccess().getIdentifierIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getChangeIdentifierAssignmentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"identifier",
            						lv_identifier_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getChangeIdentifierAssignmentAccess().getToKeyword_2());
            		
            // InternalLea.g:212:3: ( (lv_elements_3_0= RULE_ID ) )
            // InternalLea.g:213:4: (lv_elements_3_0= RULE_ID )
            {
            // InternalLea.g:213:4: (lv_elements_3_0= RULE_ID )
            // InternalLea.g:214:5: lv_elements_3_0= RULE_ID
            {
            lv_elements_3_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_elements_3_0, grammarAccess.getChangeIdentifierAssignmentAccess().getElementsIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getChangeIdentifierAssignmentRule());
            					}
            					addWithLastConsumed(
            						current,
            						"elements",
            						lv_elements_3_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalLea.g:230:3: (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==13) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalLea.g:231:4: otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,13,FOLLOW_4); 

            	    				newLeafNode(otherlv_4, grammarAccess.getChangeIdentifierAssignmentAccess().getCommaKeyword_4_0());
            	    			
            	    // InternalLea.g:235:4: ( (lv_elements_5_0= RULE_ID ) )
            	    // InternalLea.g:236:5: (lv_elements_5_0= RULE_ID )
            	    {
            	    // InternalLea.g:236:5: (lv_elements_5_0= RULE_ID )
            	    // InternalLea.g:237:6: lv_elements_5_0= RULE_ID
            	    {
            	    lv_elements_5_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            	    						newLeafNode(lv_elements_5_0, grammarAccess.getChangeIdentifierAssignmentAccess().getElementsIDTerminalRuleCall_4_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getChangeIdentifierAssignmentRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"elements",
            	    							lv_elements_5_0,
            	    							"org.eclipse.xtext.common.Terminals.ID");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalLea.g:254:3: (otherlv_6= ';' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalLea.g:255:4: otherlv_6= ';'
                    {
                    otherlv_6=(Token)match(input,14,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getChangeIdentifierAssignmentAccess().getSemicolonKeyword_5());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleChangeIdentifierAssignment"


    // $ANTLR start "entryRuleArtifactDeclaration"
    // InternalLea.g:264:1: entryRuleArtifactDeclaration returns [EObject current=null] : iv_ruleArtifactDeclaration= ruleArtifactDeclaration EOF ;
    public final EObject entryRuleArtifactDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArtifactDeclaration = null;


        try {
            // InternalLea.g:264:60: (iv_ruleArtifactDeclaration= ruleArtifactDeclaration EOF )
            // InternalLea.g:265:2: iv_ruleArtifactDeclaration= ruleArtifactDeclaration EOF
            {
             newCompositeNode(grammarAccess.getArtifactDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArtifactDeclaration=ruleArtifactDeclaration();

            state._fsp--;

             current =iv_ruleArtifactDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArtifactDeclaration"


    // $ANTLR start "ruleArtifactDeclaration"
    // InternalLea.g:271:1: ruleArtifactDeclaration returns [EObject current=null] : (otherlv_0= 'Artifact' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) ;
    public final EObject ruleArtifactDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_type_2_0=null;
        Token otherlv_3=null;
        Token lv_name_5_0=null;
        Token otherlv_7=null;
        EObject lv_set_4_0 = null;

        EObject lv_initialization_6_0 = null;



        	enterRule();

        try {
            // InternalLea.g:277:2: ( (otherlv_0= 'Artifact' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) )
            // InternalLea.g:278:2: (otherlv_0= 'Artifact' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            {
            // InternalLea.g:278:2: (otherlv_0= 'Artifact' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            // InternalLea.g:279:3: otherlv_0= 'Artifact' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )?
            {
            otherlv_0=(Token)match(input,15,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getArtifactDeclarationAccess().getArtifactKeyword_0());
            		
            otherlv_1=(Token)match(input,16,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getArtifactDeclarationAccess().getLessThanSignKeyword_1());
            		
            // InternalLea.g:287:3: ( (lv_type_2_0= RULE_ID ) )
            // InternalLea.g:288:4: (lv_type_2_0= RULE_ID )
            {
            // InternalLea.g:288:4: (lv_type_2_0= RULE_ID )
            // InternalLea.g:289:5: lv_type_2_0= RULE_ID
            {
            lv_type_2_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_type_2_0, grammarAccess.getArtifactDeclarationAccess().getTypeIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getArtifactDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getArtifactDeclarationAccess().getGreaterThanSignKeyword_3());
            		
            // InternalLea.g:309:3: ( (lv_set_4_0= ruleSetDefinition ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==20) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalLea.g:310:4: (lv_set_4_0= ruleSetDefinition )
                    {
                    // InternalLea.g:310:4: (lv_set_4_0= ruleSetDefinition )
                    // InternalLea.g:311:5: lv_set_4_0= ruleSetDefinition
                    {

                    					newCompositeNode(grammarAccess.getArtifactDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_4);
                    lv_set_4_0=ruleSetDefinition();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getArtifactDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"set",
                    						lv_set_4_0,
                    						"net.ssehub.Lea.SetDefinition");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalLea.g:328:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalLea.g:329:4: (lv_name_5_0= RULE_ID )
            {
            // InternalLea.g:329:4: (lv_name_5_0= RULE_ID )
            // InternalLea.g:330:5: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(lv_name_5_0, grammarAccess.getArtifactDeclarationAccess().getNameIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getArtifactDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_5_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalLea.g:346:3: ( (lv_initialization_6_0= ruleAssignment ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==23) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalLea.g:347:4: (lv_initialization_6_0= ruleAssignment )
                    {
                    // InternalLea.g:347:4: (lv_initialization_6_0= ruleAssignment )
                    // InternalLea.g:348:5: lv_initialization_6_0= ruleAssignment
                    {

                    					newCompositeNode(grammarAccess.getArtifactDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_initialization_6_0=ruleAssignment();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getArtifactDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"initialization",
                    						lv_initialization_6_0,
                    						"net.ssehub.Lea.Assignment");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalLea.g:365:3: (otherlv_7= ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==14) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalLea.g:366:4: otherlv_7= ';'
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_2); 

                    				newLeafNode(otherlv_7, grammarAccess.getArtifactDeclarationAccess().getSemicolonKeyword_7());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArtifactDeclaration"


    // $ANTLR start "entryRuleFragmentDeclaration"
    // InternalLea.g:375:1: entryRuleFragmentDeclaration returns [EObject current=null] : iv_ruleFragmentDeclaration= ruleFragmentDeclaration EOF ;
    public final EObject entryRuleFragmentDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFragmentDeclaration = null;


        try {
            // InternalLea.g:375:60: (iv_ruleFragmentDeclaration= ruleFragmentDeclaration EOF )
            // InternalLea.g:376:2: iv_ruleFragmentDeclaration= ruleFragmentDeclaration EOF
            {
             newCompositeNode(grammarAccess.getFragmentDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFragmentDeclaration=ruleFragmentDeclaration();

            state._fsp--;

             current =iv_ruleFragmentDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFragmentDeclaration"


    // $ANTLR start "ruleFragmentDeclaration"
    // InternalLea.g:382:1: ruleFragmentDeclaration returns [EObject current=null] : (otherlv_0= 'Fragment' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) ;
    public final EObject ruleFragmentDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_type_2_0=null;
        Token otherlv_3=null;
        Token lv_name_5_0=null;
        Token otherlv_7=null;
        EObject lv_set_4_0 = null;

        EObject lv_initialization_6_0 = null;



        	enterRule();

        try {
            // InternalLea.g:388:2: ( (otherlv_0= 'Fragment' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) )
            // InternalLea.g:389:2: (otherlv_0= 'Fragment' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            {
            // InternalLea.g:389:2: (otherlv_0= 'Fragment' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            // InternalLea.g:390:3: otherlv_0= 'Fragment' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )?
            {
            otherlv_0=(Token)match(input,18,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getFragmentDeclarationAccess().getFragmentKeyword_0());
            		
            otherlv_1=(Token)match(input,16,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getFragmentDeclarationAccess().getLessThanSignKeyword_1());
            		
            // InternalLea.g:398:3: ( (lv_type_2_0= RULE_ID ) )
            // InternalLea.g:399:4: (lv_type_2_0= RULE_ID )
            {
            // InternalLea.g:399:4: (lv_type_2_0= RULE_ID )
            // InternalLea.g:400:5: lv_type_2_0= RULE_ID
            {
            lv_type_2_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_type_2_0, grammarAccess.getFragmentDeclarationAccess().getTypeIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFragmentDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getFragmentDeclarationAccess().getGreaterThanSignKeyword_3());
            		
            // InternalLea.g:420:3: ( (lv_set_4_0= ruleSetDefinition ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==20) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalLea.g:421:4: (lv_set_4_0= ruleSetDefinition )
                    {
                    // InternalLea.g:421:4: (lv_set_4_0= ruleSetDefinition )
                    // InternalLea.g:422:5: lv_set_4_0= ruleSetDefinition
                    {

                    					newCompositeNode(grammarAccess.getFragmentDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_4);
                    lv_set_4_0=ruleSetDefinition();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFragmentDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"set",
                    						lv_set_4_0,
                    						"net.ssehub.Lea.SetDefinition");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalLea.g:439:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalLea.g:440:4: (lv_name_5_0= RULE_ID )
            {
            // InternalLea.g:440:4: (lv_name_5_0= RULE_ID )
            // InternalLea.g:441:5: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(lv_name_5_0, grammarAccess.getFragmentDeclarationAccess().getNameIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFragmentDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_5_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalLea.g:457:3: ( (lv_initialization_6_0= ruleAssignment ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalLea.g:458:4: (lv_initialization_6_0= ruleAssignment )
                    {
                    // InternalLea.g:458:4: (lv_initialization_6_0= ruleAssignment )
                    // InternalLea.g:459:5: lv_initialization_6_0= ruleAssignment
                    {

                    					newCompositeNode(grammarAccess.getFragmentDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_initialization_6_0=ruleAssignment();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFragmentDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"initialization",
                    						lv_initialization_6_0,
                    						"net.ssehub.Lea.Assignment");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalLea.g:476:3: (otherlv_7= ';' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==14) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalLea.g:477:4: otherlv_7= ';'
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_2); 

                    				newLeafNode(otherlv_7, grammarAccess.getFragmentDeclarationAccess().getSemicolonKeyword_7());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFragmentDeclaration"


    // $ANTLR start "entryRuleResultDeclaration"
    // InternalLea.g:486:1: entryRuleResultDeclaration returns [EObject current=null] : iv_ruleResultDeclaration= ruleResultDeclaration EOF ;
    public final EObject entryRuleResultDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResultDeclaration = null;


        try {
            // InternalLea.g:486:58: (iv_ruleResultDeclaration= ruleResultDeclaration EOF )
            // InternalLea.g:487:2: iv_ruleResultDeclaration= ruleResultDeclaration EOF
            {
             newCompositeNode(grammarAccess.getResultDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResultDeclaration=ruleResultDeclaration();

            state._fsp--;

             current =iv_ruleResultDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleResultDeclaration"


    // $ANTLR start "ruleResultDeclaration"
    // InternalLea.g:493:1: ruleResultDeclaration returns [EObject current=null] : (otherlv_0= 'Result' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) ;
    public final EObject ruleResultDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_type_2_0=null;
        Token otherlv_3=null;
        Token lv_name_5_0=null;
        Token otherlv_7=null;
        EObject lv_set_4_0 = null;

        EObject lv_initialization_6_0 = null;



        	enterRule();

        try {
            // InternalLea.g:499:2: ( (otherlv_0= 'Result' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) )
            // InternalLea.g:500:2: (otherlv_0= 'Result' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            {
            // InternalLea.g:500:2: (otherlv_0= 'Result' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            // InternalLea.g:501:3: otherlv_0= 'Result' otherlv_1= '<' ( (lv_type_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )?
            {
            otherlv_0=(Token)match(input,19,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getResultDeclarationAccess().getResultKeyword_0());
            		
            otherlv_1=(Token)match(input,16,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getResultDeclarationAccess().getLessThanSignKeyword_1());
            		
            // InternalLea.g:509:3: ( (lv_type_2_0= RULE_ID ) )
            // InternalLea.g:510:4: (lv_type_2_0= RULE_ID )
            {
            // InternalLea.g:510:4: (lv_type_2_0= RULE_ID )
            // InternalLea.g:511:5: lv_type_2_0= RULE_ID
            {
            lv_type_2_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_type_2_0, grammarAccess.getResultDeclarationAccess().getTypeIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResultDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getResultDeclarationAccess().getGreaterThanSignKeyword_3());
            		
            // InternalLea.g:531:3: ( (lv_set_4_0= ruleSetDefinition ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==20) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalLea.g:532:4: (lv_set_4_0= ruleSetDefinition )
                    {
                    // InternalLea.g:532:4: (lv_set_4_0= ruleSetDefinition )
                    // InternalLea.g:533:5: lv_set_4_0= ruleSetDefinition
                    {

                    					newCompositeNode(grammarAccess.getResultDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_4);
                    lv_set_4_0=ruleSetDefinition();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getResultDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"set",
                    						lv_set_4_0,
                    						"net.ssehub.Lea.SetDefinition");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalLea.g:550:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalLea.g:551:4: (lv_name_5_0= RULE_ID )
            {
            // InternalLea.g:551:4: (lv_name_5_0= RULE_ID )
            // InternalLea.g:552:5: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(lv_name_5_0, grammarAccess.getResultDeclarationAccess().getNameIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResultDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_5_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalLea.g:568:3: ( (lv_initialization_6_0= ruleAssignment ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLea.g:569:4: (lv_initialization_6_0= ruleAssignment )
                    {
                    // InternalLea.g:569:4: (lv_initialization_6_0= ruleAssignment )
                    // InternalLea.g:570:5: lv_initialization_6_0= ruleAssignment
                    {

                    					newCompositeNode(grammarAccess.getResultDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_initialization_6_0=ruleAssignment();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getResultDeclarationRule());
                    					}
                    					set(
                    						current,
                    						"initialization",
                    						lv_initialization_6_0,
                    						"net.ssehub.Lea.Assignment");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalLea.g:587:3: (otherlv_7= ';' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==14) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalLea.g:588:4: otherlv_7= ';'
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_2); 

                    				newLeafNode(otherlv_7, grammarAccess.getResultDeclarationAccess().getSemicolonKeyword_7());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResultDeclaration"


    // $ANTLR start "entryRuleSetDefinition"
    // InternalLea.g:597:1: entryRuleSetDefinition returns [EObject current=null] : iv_ruleSetDefinition= ruleSetDefinition EOF ;
    public final EObject entryRuleSetDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetDefinition = null;


        try {
            // InternalLea.g:597:54: (iv_ruleSetDefinition= ruleSetDefinition EOF )
            // InternalLea.g:598:2: iv_ruleSetDefinition= ruleSetDefinition EOF
            {
             newCompositeNode(grammarAccess.getSetDefinitionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetDefinition=ruleSetDefinition();

            state._fsp--;

             current =iv_ruleSetDefinition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetDefinition"


    // $ANTLR start "ruleSetDefinition"
    // InternalLea.g:604:1: ruleSetDefinition returns [EObject current=null] : ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' ) ;
    public final EObject ruleSetDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_iteration_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:610:2: ( ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' ) )
            // InternalLea.g:611:2: ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' )
            {
            // InternalLea.g:611:2: ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' )
            // InternalLea.g:612:3: () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']'
            {
            // InternalLea.g:612:3: ()
            // InternalLea.g:613:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetDefinitionAccess().getSetDefinitionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,20,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getSetDefinitionAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalLea.g:623:3: ( (lv_iteration_2_0= ruleIteration ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalLea.g:624:4: (lv_iteration_2_0= ruleIteration )
                    {
                    // InternalLea.g:624:4: (lv_iteration_2_0= ruleIteration )
                    // InternalLea.g:625:5: lv_iteration_2_0= ruleIteration
                    {

                    					newCompositeNode(grammarAccess.getSetDefinitionAccess().getIterationIterationParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_13);
                    lv_iteration_2_0=ruleIteration();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getSetDefinitionRule());
                    					}
                    					set(
                    						current,
                    						"iteration",
                    						lv_iteration_2_0,
                    						"net.ssehub.Lea.Iteration");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getSetDefinitionAccess().getRightSquareBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetDefinition"


    // $ANTLR start "entryRuleIteration"
    // InternalLea.g:650:1: entryRuleIteration returns [EObject current=null] : iv_ruleIteration= ruleIteration EOF ;
    public final EObject entryRuleIteration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIteration = null;


        try {
            // InternalLea.g:650:50: (iv_ruleIteration= ruleIteration EOF )
            // InternalLea.g:651:2: iv_ruleIteration= ruleIteration EOF
            {
             newCompositeNode(grammarAccess.getIterationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIteration=ruleIteration();

            state._fsp--;

             current =iv_ruleIteration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIteration"


    // $ANTLR start "ruleIteration"
    // InternalLea.g:657:1: ruleIteration returns [EObject current=null] : ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) ) ;
    public final EObject ruleIteration() throws RecognitionException {
        EObject current = null;

        Token lv_iterator_0_0=null;
        Token otherlv_1=null;
        Token lv_iterable_2_0=null;


        	enterRule();

        try {
            // InternalLea.g:663:2: ( ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) ) )
            // InternalLea.g:664:2: ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) )
            {
            // InternalLea.g:664:2: ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) )
            // InternalLea.g:665:3: ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) )
            {
            // InternalLea.g:665:3: ( (lv_iterator_0_0= RULE_ID ) )
            // InternalLea.g:666:4: (lv_iterator_0_0= RULE_ID )
            {
            // InternalLea.g:666:4: (lv_iterator_0_0= RULE_ID )
            // InternalLea.g:667:5: lv_iterator_0_0= RULE_ID
            {
            lv_iterator_0_0=(Token)match(input,RULE_ID,FOLLOW_14); 

            					newLeafNode(lv_iterator_0_0, grammarAccess.getIterationAccess().getIteratorIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIterationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iterator",
            						lv_iterator_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getIterationAccess().getColonKeyword_1());
            		
            // InternalLea.g:687:3: ( (lv_iterable_2_0= RULE_ID ) )
            // InternalLea.g:688:4: (lv_iterable_2_0= RULE_ID )
            {
            // InternalLea.g:688:4: (lv_iterable_2_0= RULE_ID )
            // InternalLea.g:689:5: lv_iterable_2_0= RULE_ID
            {
            lv_iterable_2_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_iterable_2_0, grammarAccess.getIterationAccess().getIterableIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIterationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iterable",
            						lv_iterable_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIteration"


    // $ANTLR start "entryRuleAssignment"
    // InternalLea.g:709:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalLea.g:709:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalLea.g:710:2: iv_ruleAssignment= ruleAssignment EOF
            {
             newCompositeNode(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssignment=ruleAssignment();

            state._fsp--;

             current =iv_ruleAssignment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalLea.g:716:1: ruleAssignment returns [EObject current=null] : (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_element_1_0=null;
        EObject lv_operation_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:722:2: ( (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) ) )
            // InternalLea.g:723:2: (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) )
            {
            // InternalLea.g:723:2: (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) )
            // InternalLea.g:724:3: otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getAssignmentAccess().getEqualsSignKeyword_0());
            		
            // InternalLea.g:728:3: ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_ID) ) {
                int LA14_1 = input.LA(2);

                if ( ((LA14_1>=24 && LA14_1<=25)) ) {
                    alt14=2;
                }
                else if ( (LA14_1==EOF||LA14_1==11||(LA14_1>=14 && LA14_1<=15)||(LA14_1>=18 && LA14_1<=19)) ) {
                    alt14=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // InternalLea.g:729:4: ( (lv_element_1_0= RULE_ID ) )
                    {
                    // InternalLea.g:729:4: ( (lv_element_1_0= RULE_ID ) )
                    // InternalLea.g:730:5: (lv_element_1_0= RULE_ID )
                    {
                    // InternalLea.g:730:5: (lv_element_1_0= RULE_ID )
                    // InternalLea.g:731:6: lv_element_1_0= RULE_ID
                    {
                    lv_element_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(lv_element_1_0, grammarAccess.getAssignmentAccess().getElementIDTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssignmentRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"element",
                    							lv_element_1_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLea.g:748:4: ( (lv_operation_2_0= ruleOperation ) )
                    {
                    // InternalLea.g:748:4: ( (lv_operation_2_0= ruleOperation ) )
                    // InternalLea.g:749:5: (lv_operation_2_0= ruleOperation )
                    {
                    // InternalLea.g:749:5: (lv_operation_2_0= ruleOperation )
                    // InternalLea.g:750:6: lv_operation_2_0= ruleOperation
                    {

                    						newCompositeNode(grammarAccess.getAssignmentAccess().getOperationOperationParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_operation_2_0=ruleOperation();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAssignmentRule());
                    						}
                    						set(
                    							current,
                    							"operation",
                    							lv_operation_2_0,
                    							"net.ssehub.Lea.Operation");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleOperation"
    // InternalLea.g:772:1: entryRuleOperation returns [EObject current=null] : iv_ruleOperation= ruleOperation EOF ;
    public final EObject entryRuleOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation = null;


        try {
            // InternalLea.g:772:50: (iv_ruleOperation= ruleOperation EOF )
            // InternalLea.g:773:2: iv_ruleOperation= ruleOperation EOF
            {
             newCompositeNode(grammarAccess.getOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperation=ruleOperation();

            state._fsp--;

             current =iv_ruleOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation"


    // $ANTLR start "ruleOperation"
    // InternalLea.g:779:1: ruleOperation returns [EObject current=null] : ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? ) ;
    public final EObject ruleOperation() throws RecognitionException {
        EObject current = null;

        Token lv_element_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_call_2_0 = null;

        EObject lv_call_4_0 = null;



        	enterRule();

        try {
            // InternalLea.g:785:2: ( ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? ) )
            // InternalLea.g:786:2: ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? )
            {
            // InternalLea.g:786:2: ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? )
            // InternalLea.g:787:3: ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )?
            {
            // InternalLea.g:787:3: ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==24) ) {
                    alt15=1;
                }
            }
            switch (alt15) {
                case 1 :
                    // InternalLea.g:788:4: ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.'
                    {
                    // InternalLea.g:788:4: ( (lv_element_0_0= RULE_ID ) )
                    // InternalLea.g:789:5: (lv_element_0_0= RULE_ID )
                    {
                    // InternalLea.g:789:5: (lv_element_0_0= RULE_ID )
                    // InternalLea.g:790:6: lv_element_0_0= RULE_ID
                    {
                    lv_element_0_0=(Token)match(input,RULE_ID,FOLLOW_15); 

                    						newLeafNode(lv_element_0_0, grammarAccess.getOperationAccess().getElementIDTerminalRuleCall_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getOperationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"element",
                    							lv_element_0_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }

                    otherlv_1=(Token)match(input,24,FOLLOW_4); 

                    				newLeafNode(otherlv_1, grammarAccess.getOperationAccess().getFullStopKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalLea.g:811:3: ( (lv_call_2_0= ruleCall ) )
            // InternalLea.g:812:4: (lv_call_2_0= ruleCall )
            {
            // InternalLea.g:812:4: (lv_call_2_0= ruleCall )
            // InternalLea.g:813:5: lv_call_2_0= ruleCall
            {

            					newCompositeNode(grammarAccess.getOperationAccess().getCallCallParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_16);
            lv_call_2_0=ruleCall();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOperationRule());
            					}
            					add(
            						current,
            						"call",
            						lv_call_2_0,
            						"net.ssehub.Lea.Call");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalLea.g:830:3: (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==24) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalLea.g:831:4: otherlv_3= '.' ( (lv_call_4_0= ruleCall ) )
                    {
                    otherlv_3=(Token)match(input,24,FOLLOW_4); 

                    				newLeafNode(otherlv_3, grammarAccess.getOperationAccess().getFullStopKeyword_2_0());
                    			
                    // InternalLea.g:835:4: ( (lv_call_4_0= ruleCall ) )
                    // InternalLea.g:836:5: (lv_call_4_0= ruleCall )
                    {
                    // InternalLea.g:836:5: (lv_call_4_0= ruleCall )
                    // InternalLea.g:837:6: lv_call_4_0= ruleCall
                    {

                    						newCompositeNode(grammarAccess.getOperationAccess().getCallCallParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_call_4_0=ruleCall();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getOperationRule());
                    						}
                    						add(
                    							current,
                    							"call",
                    							lv_call_4_0,
                    							"net.ssehub.Lea.Call");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation"


    // $ANTLR start "entryRuleCall"
    // InternalLea.g:859:1: entryRuleCall returns [EObject current=null] : iv_ruleCall= ruleCall EOF ;
    public final EObject entryRuleCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCall = null;


        try {
            // InternalLea.g:859:45: (iv_ruleCall= ruleCall EOF )
            // InternalLea.g:860:2: iv_ruleCall= ruleCall EOF
            {
             newCompositeNode(grammarAccess.getCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCall=ruleCall();

            state._fsp--;

             current =iv_ruleCall; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCall"


    // $ANTLR start "ruleCall"
    // InternalLea.g:866:1: ruleCall returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' ) ;
    public final EObject ruleCall() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_parameters_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:872:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' ) )
            // InternalLea.g:873:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' )
            {
            // InternalLea.g:873:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' )
            // InternalLea.g:874:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')'
            {
            // InternalLea.g:874:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalLea.g:875:4: (lv_name_0_0= RULE_ID )
            {
            // InternalLea.g:875:4: (lv_name_0_0= RULE_ID )
            // InternalLea.g:876:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_17); 

            					newLeafNode(lv_name_0_0, grammarAccess.getCallAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCallRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,25,FOLLOW_18); 

            			newLeafNode(otherlv_1, grammarAccess.getCallAccess().getLeftParenthesisKeyword_1());
            		
            // InternalLea.g:896:3: ( (lv_parameters_2_0= ruleParameterList ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=RULE_ID && LA17_0<=RULE_STRING)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalLea.g:897:4: (lv_parameters_2_0= ruleParameterList )
                    {
                    // InternalLea.g:897:4: (lv_parameters_2_0= ruleParameterList )
                    // InternalLea.g:898:5: lv_parameters_2_0= ruleParameterList
                    {

                    					newCompositeNode(grammarAccess.getCallAccess().getParametersParameterListParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_19);
                    lv_parameters_2_0=ruleParameterList();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getCallRule());
                    					}
                    					set(
                    						current,
                    						"parameters",
                    						lv_parameters_2_0,
                    						"net.ssehub.Lea.ParameterList");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,26,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getCallAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCall"


    // $ANTLR start "entryRuleParameterList"
    // InternalLea.g:923:1: entryRuleParameterList returns [EObject current=null] : iv_ruleParameterList= ruleParameterList EOF ;
    public final EObject entryRuleParameterList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterList = null;


        try {
            // InternalLea.g:923:54: (iv_ruleParameterList= ruleParameterList EOF )
            // InternalLea.g:924:2: iv_ruleParameterList= ruleParameterList EOF
            {
             newCompositeNode(grammarAccess.getParameterListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameterList=ruleParameterList();

            state._fsp--;

             current =iv_ruleParameterList; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterList"


    // $ANTLR start "ruleParameterList"
    // InternalLea.g:930:1: ruleParameterList returns [EObject current=null] : ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* ) ;
    public final EObject ruleParameterList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_parameterList_0_0 = null;

        EObject lv_parameterList_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:936:2: ( ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* ) )
            // InternalLea.g:937:2: ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* )
            {
            // InternalLea.g:937:2: ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* )
            // InternalLea.g:938:3: ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )*
            {
            // InternalLea.g:938:3: ( (lv_parameterList_0_0= ruleParameter ) )
            // InternalLea.g:939:4: (lv_parameterList_0_0= ruleParameter )
            {
            // InternalLea.g:939:4: (lv_parameterList_0_0= ruleParameter )
            // InternalLea.g:940:5: lv_parameterList_0_0= ruleParameter
            {

            					newCompositeNode(grammarAccess.getParameterListAccess().getParameterListParameterParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_20);
            lv_parameterList_0_0=ruleParameter();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParameterListRule());
            					}
            					add(
            						current,
            						"parameterList",
            						lv_parameterList_0_0,
            						"net.ssehub.Lea.Parameter");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalLea.g:957:3: (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==13) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalLea.g:958:4: otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) )
            	    {
            	    otherlv_1=(Token)match(input,13,FOLLOW_21); 

            	    				newLeafNode(otherlv_1, grammarAccess.getParameterListAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalLea.g:962:4: ( (lv_parameterList_2_0= ruleParameter ) )
            	    // InternalLea.g:963:5: (lv_parameterList_2_0= ruleParameter )
            	    {
            	    // InternalLea.g:963:5: (lv_parameterList_2_0= ruleParameter )
            	    // InternalLea.g:964:6: lv_parameterList_2_0= ruleParameter
            	    {

            	    						newCompositeNode(grammarAccess.getParameterListAccess().getParameterListParameterParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_20);
            	    lv_parameterList_2_0=ruleParameter();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParameterListRule());
            	    						}
            	    						add(
            	    							current,
            	    							"parameterList",
            	    							lv_parameterList_2_0,
            	    							"net.ssehub.Lea.Parameter");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterList"


    // $ANTLR start "entryRuleParameter"
    // InternalLea.g:986:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalLea.g:986:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalLea.g:987:2: iv_ruleParameter= ruleParameter EOF
            {
             newCompositeNode(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalLea.g:993:1: ruleParameter returns [EObject current=null] : ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token lv_text_0_0=null;
        Token lv_element_1_0=null;
        EObject lv_operation_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:999:2: ( ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) )
            // InternalLea.g:1000:2: ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            {
            // InternalLea.g:1000:2: ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            int alt19=3;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_STRING) ) {
                alt19=1;
            }
            else if ( (LA19_0==RULE_ID) ) {
                int LA19_2 = input.LA(2);

                if ( (LA19_2==EOF||LA19_2==13||LA19_2==26) ) {
                    alt19=2;
                }
                else if ( ((LA19_2>=24 && LA19_2<=25)) ) {
                    alt19=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalLea.g:1001:3: ( (lv_text_0_0= RULE_STRING ) )
                    {
                    // InternalLea.g:1001:3: ( (lv_text_0_0= RULE_STRING ) )
                    // InternalLea.g:1002:4: (lv_text_0_0= RULE_STRING )
                    {
                    // InternalLea.g:1002:4: (lv_text_0_0= RULE_STRING )
                    // InternalLea.g:1003:5: lv_text_0_0= RULE_STRING
                    {
                    lv_text_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    					newLeafNode(lv_text_0_0, grammarAccess.getParameterAccess().getTextSTRINGTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getParameterRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"text",
                    						lv_text_0_0,
                    						"org.eclipse.xtext.common.Terminals.STRING");
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLea.g:1020:3: ( (lv_element_1_0= RULE_ID ) )
                    {
                    // InternalLea.g:1020:3: ( (lv_element_1_0= RULE_ID ) )
                    // InternalLea.g:1021:4: (lv_element_1_0= RULE_ID )
                    {
                    // InternalLea.g:1021:4: (lv_element_1_0= RULE_ID )
                    // InternalLea.g:1022:5: lv_element_1_0= RULE_ID
                    {
                    lv_element_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    					newLeafNode(lv_element_1_0, grammarAccess.getParameterAccess().getElementIDTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getParameterRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"element",
                    						lv_element_1_0,
                    						"org.eclipse.xtext.common.Terminals.ID");
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLea.g:1039:3: ( (lv_operation_2_0= ruleOperation ) )
                    {
                    // InternalLea.g:1039:3: ( (lv_operation_2_0= ruleOperation ) )
                    // InternalLea.g:1040:4: (lv_operation_2_0= ruleOperation )
                    {
                    // InternalLea.g:1040:4: (lv_operation_2_0= ruleOperation )
                    // InternalLea.g:1041:5: lv_operation_2_0= ruleOperation
                    {

                    					newCompositeNode(grammarAccess.getParameterAccess().getOperationOperationParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_operation_2_0=ruleOperation();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getParameterRule());
                    					}
                    					set(
                    						current,
                    						"operation",
                    						lv_operation_2_0,
                    						"net.ssehub.Lea.Operation");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000000000C8802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000100010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000804002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000004000030L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000030L});

}