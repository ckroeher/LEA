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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'assign'", "'to'", "','", "';'", "'<'", "'>'", "'Artifact'", "'Fragment'", "'Result'", "'['", "']'", "':'", "'='", "'.'", "'('", "')'"
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
    // InternalLea.g:71:1: ruleAnalysisDefinition returns [EObject current=null] : ( () ( ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) ) | ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) ) )* ) ;
    public final EObject ruleAnalysisDefinition() throws RecognitionException {
        EObject current = null;

        EObject lv_elementDeclarations_1_0 = null;

        EObject lv_changeIdentifierAssignments_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:77:2: ( ( () ( ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) ) | ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) ) )* ) )
            // InternalLea.g:78:2: ( () ( ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) ) | ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) ) )* )
            {
            // InternalLea.g:78:2: ( () ( ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) ) | ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) ) )* )
            // InternalLea.g:79:3: () ( ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) ) | ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) ) )*
            {
            // InternalLea.g:79:3: ()
            // InternalLea.g:80:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAnalysisDefinitionAccess().getAnalysisDefinitionAction_0(),
            					current);
            			

            }

            // InternalLea.g:86:3: ( ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) ) | ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) ) )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=17 && LA1_0<=19)) ) {
                    alt1=1;
                }
                else if ( (LA1_0==11) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalLea.g:87:4: ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) )
            	    {
            	    // InternalLea.g:87:4: ( (lv_elementDeclarations_1_0= ruleElementDeclaration ) )
            	    // InternalLea.g:88:5: (lv_elementDeclarations_1_0= ruleElementDeclaration )
            	    {
            	    // InternalLea.g:88:5: (lv_elementDeclarations_1_0= ruleElementDeclaration )
            	    // InternalLea.g:89:6: lv_elementDeclarations_1_0= ruleElementDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getAnalysisDefinitionAccess().getElementDeclarationsElementDeclarationParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_elementDeclarations_1_0=ruleElementDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAnalysisDefinitionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elementDeclarations",
            	    							lv_elementDeclarations_1_0,
            	    							"net.ssehub.Lea.ElementDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalLea.g:107:4: ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) )
            	    {
            	    // InternalLea.g:107:4: ( (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment ) )
            	    // InternalLea.g:108:5: (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment )
            	    {
            	    // InternalLea.g:108:5: (lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment )
            	    // InternalLea.g:109:6: lv_changeIdentifierAssignments_2_0= ruleChangeIdentifierAssignment
            	    {

            	    						newCompositeNode(grammarAccess.getAnalysisDefinitionAccess().getChangeIdentifierAssignmentsChangeIdentifierAssignmentParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_changeIdentifierAssignments_2_0=ruleChangeIdentifierAssignment();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAnalysisDefinitionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"changeIdentifierAssignments",
            	    							lv_changeIdentifierAssignments_2_0,
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
    // InternalLea.g:131:1: entryRuleChangeIdentifierAssignment returns [EObject current=null] : iv_ruleChangeIdentifierAssignment= ruleChangeIdentifierAssignment EOF ;
    public final EObject entryRuleChangeIdentifierAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChangeIdentifierAssignment = null;


        try {
            // InternalLea.g:131:67: (iv_ruleChangeIdentifierAssignment= ruleChangeIdentifierAssignment EOF )
            // InternalLea.g:132:2: iv_ruleChangeIdentifierAssignment= ruleChangeIdentifierAssignment EOF
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
    // InternalLea.g:138:1: ruleChangeIdentifierAssignment returns [EObject current=null] : (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? ) ;
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
            // InternalLea.g:144:2: ( (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? ) )
            // InternalLea.g:145:2: (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? )
            {
            // InternalLea.g:145:2: (otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )? )
            // InternalLea.g:146:3: otherlv_0= 'assign' ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= 'to' ( (lv_elements_3_0= RULE_ID ) ) (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )* (otherlv_6= ';' )?
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getChangeIdentifierAssignmentAccess().getAssignKeyword_0());
            		
            // InternalLea.g:150:3: ( (lv_identifier_1_0= RULE_ID ) )
            // InternalLea.g:151:4: (lv_identifier_1_0= RULE_ID )
            {
            // InternalLea.g:151:4: (lv_identifier_1_0= RULE_ID )
            // InternalLea.g:152:5: lv_identifier_1_0= RULE_ID
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
            		
            // InternalLea.g:172:3: ( (lv_elements_3_0= RULE_ID ) )
            // InternalLea.g:173:4: (lv_elements_3_0= RULE_ID )
            {
            // InternalLea.g:173:4: (lv_elements_3_0= RULE_ID )
            // InternalLea.g:174:5: lv_elements_3_0= RULE_ID
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

            // InternalLea.g:190:3: (otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==13) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalLea.g:191:4: otherlv_4= ',' ( (lv_elements_5_0= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,13,FOLLOW_4); 

            	    				newLeafNode(otherlv_4, grammarAccess.getChangeIdentifierAssignmentAccess().getCommaKeyword_4_0());
            	    			
            	    // InternalLea.g:195:4: ( (lv_elements_5_0= RULE_ID ) )
            	    // InternalLea.g:196:5: (lv_elements_5_0= RULE_ID )
            	    {
            	    // InternalLea.g:196:5: (lv_elements_5_0= RULE_ID )
            	    // InternalLea.g:197:6: lv_elements_5_0= RULE_ID
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

            // InternalLea.g:214:3: (otherlv_6= ';' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalLea.g:215:4: otherlv_6= ';'
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


    // $ANTLR start "entryRuleElementDeclaration"
    // InternalLea.g:224:1: entryRuleElementDeclaration returns [EObject current=null] : iv_ruleElementDeclaration= ruleElementDeclaration EOF ;
    public final EObject entryRuleElementDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementDeclaration = null;


        try {
            // InternalLea.g:224:59: (iv_ruleElementDeclaration= ruleElementDeclaration EOF )
            // InternalLea.g:225:2: iv_ruleElementDeclaration= ruleElementDeclaration EOF
            {
             newCompositeNode(grammarAccess.getElementDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElementDeclaration=ruleElementDeclaration();

            state._fsp--;

             current =iv_ruleElementDeclaration; 
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
    // $ANTLR end "entryRuleElementDeclaration"


    // $ANTLR start "ruleElementDeclaration"
    // InternalLea.g:231:1: ruleElementDeclaration returns [EObject current=null] : ( ( (lv_genericTyp_0_0= ruleGenericType ) ) otherlv_1= '<' ( (lv_parameterType_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) ;
    public final EObject ruleElementDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_parameterType_2_0=null;
        Token otherlv_3=null;
        Token lv_name_5_0=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_genericTyp_0_0 = null;

        EObject lv_set_4_0 = null;

        EObject lv_initialization_6_0 = null;



        	enterRule();

        try {
            // InternalLea.g:237:2: ( ( ( (lv_genericTyp_0_0= ruleGenericType ) ) otherlv_1= '<' ( (lv_parameterType_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? ) )
            // InternalLea.g:238:2: ( ( (lv_genericTyp_0_0= ruleGenericType ) ) otherlv_1= '<' ( (lv_parameterType_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            {
            // InternalLea.g:238:2: ( ( (lv_genericTyp_0_0= ruleGenericType ) ) otherlv_1= '<' ( (lv_parameterType_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )? )
            // InternalLea.g:239:3: ( (lv_genericTyp_0_0= ruleGenericType ) ) otherlv_1= '<' ( (lv_parameterType_2_0= RULE_ID ) ) otherlv_3= '>' ( (lv_set_4_0= ruleSetDefinition ) )? ( (lv_name_5_0= RULE_ID ) ) ( (lv_initialization_6_0= ruleAssignment ) )? (otherlv_7= ';' )?
            {
            // InternalLea.g:239:3: ( (lv_genericTyp_0_0= ruleGenericType ) )
            // InternalLea.g:240:4: (lv_genericTyp_0_0= ruleGenericType )
            {
            // InternalLea.g:240:4: (lv_genericTyp_0_0= ruleGenericType )
            // InternalLea.g:241:5: lv_genericTyp_0_0= ruleGenericType
            {

            					newCompositeNode(grammarAccess.getElementDeclarationAccess().getGenericTypGenericTypeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_7);
            lv_genericTyp_0_0=ruleGenericType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getElementDeclarationRule());
            					}
            					set(
            						current,
            						"genericTyp",
            						lv_genericTyp_0_0,
            						"net.ssehub.Lea.GenericType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,15,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getElementDeclarationAccess().getLessThanSignKeyword_1());
            		
            // InternalLea.g:262:3: ( (lv_parameterType_2_0= RULE_ID ) )
            // InternalLea.g:263:4: (lv_parameterType_2_0= RULE_ID )
            {
            // InternalLea.g:263:4: (lv_parameterType_2_0= RULE_ID )
            // InternalLea.g:264:5: lv_parameterType_2_0= RULE_ID
            {
            lv_parameterType_2_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_parameterType_2_0, grammarAccess.getElementDeclarationAccess().getParameterTypeIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElementDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"parameterType",
            						lv_parameterType_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getElementDeclarationAccess().getGreaterThanSignKeyword_3());
            		
            // InternalLea.g:284:3: ( (lv_set_4_0= ruleSetDefinition ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==20) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalLea.g:285:4: (lv_set_4_0= ruleSetDefinition )
                    {
                    // InternalLea.g:285:4: (lv_set_4_0= ruleSetDefinition )
                    // InternalLea.g:286:5: lv_set_4_0= ruleSetDefinition
                    {

                    					newCompositeNode(grammarAccess.getElementDeclarationAccess().getSetSetDefinitionParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_4);
                    lv_set_4_0=ruleSetDefinition();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getElementDeclarationRule());
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

            // InternalLea.g:303:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalLea.g:304:4: (lv_name_5_0= RULE_ID )
            {
            // InternalLea.g:304:4: (lv_name_5_0= RULE_ID )
            // InternalLea.g:305:5: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(lv_name_5_0, grammarAccess.getElementDeclarationAccess().getNameIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElementDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_5_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalLea.g:321:3: ( (lv_initialization_6_0= ruleAssignment ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==23) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalLea.g:322:4: (lv_initialization_6_0= ruleAssignment )
                    {
                    // InternalLea.g:322:4: (lv_initialization_6_0= ruleAssignment )
                    // InternalLea.g:323:5: lv_initialization_6_0= ruleAssignment
                    {

                    					newCompositeNode(grammarAccess.getElementDeclarationAccess().getInitializationAssignmentParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_initialization_6_0=ruleAssignment();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getElementDeclarationRule());
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

            // InternalLea.g:340:3: (otherlv_7= ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==14) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalLea.g:341:4: otherlv_7= ';'
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_2); 

                    				newLeafNode(otherlv_7, grammarAccess.getElementDeclarationAccess().getSemicolonKeyword_7());
                    			

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
    // $ANTLR end "ruleElementDeclaration"


    // $ANTLR start "entryRuleGenericType"
    // InternalLea.g:350:1: entryRuleGenericType returns [String current=null] : iv_ruleGenericType= ruleGenericType EOF ;
    public final String entryRuleGenericType() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleGenericType = null;


        try {
            // InternalLea.g:350:51: (iv_ruleGenericType= ruleGenericType EOF )
            // InternalLea.g:351:2: iv_ruleGenericType= ruleGenericType EOF
            {
             newCompositeNode(grammarAccess.getGenericTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGenericType=ruleGenericType();

            state._fsp--;

             current =iv_ruleGenericType.getText(); 
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
    // $ANTLR end "entryRuleGenericType"


    // $ANTLR start "ruleGenericType"
    // InternalLea.g:357:1: ruleGenericType returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Artifact' | kw= 'Fragment' | kw= 'Result' ) ;
    public final AntlrDatatypeRuleToken ruleGenericType() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLea.g:363:2: ( (kw= 'Artifact' | kw= 'Fragment' | kw= 'Result' ) )
            // InternalLea.g:364:2: (kw= 'Artifact' | kw= 'Fragment' | kw= 'Result' )
            {
            // InternalLea.g:364:2: (kw= 'Artifact' | kw= 'Fragment' | kw= 'Result' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt7=1;
                }
                break;
            case 18:
                {
                alt7=2;
                }
                break;
            case 19:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalLea.g:365:3: kw= 'Artifact'
                    {
                    kw=(Token)match(input,17,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getGenericTypeAccess().getArtifactKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalLea.g:371:3: kw= 'Fragment'
                    {
                    kw=(Token)match(input,18,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getGenericTypeAccess().getFragmentKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalLea.g:377:3: kw= 'Result'
                    {
                    kw=(Token)match(input,19,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getGenericTypeAccess().getResultKeyword_2());
                    		

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
    // $ANTLR end "ruleGenericType"


    // $ANTLR start "entryRuleSetDefinition"
    // InternalLea.g:386:1: entryRuleSetDefinition returns [EObject current=null] : iv_ruleSetDefinition= ruleSetDefinition EOF ;
    public final EObject entryRuleSetDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetDefinition = null;


        try {
            // InternalLea.g:386:54: (iv_ruleSetDefinition= ruleSetDefinition EOF )
            // InternalLea.g:387:2: iv_ruleSetDefinition= ruleSetDefinition EOF
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
    // InternalLea.g:393:1: ruleSetDefinition returns [EObject current=null] : ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' ) ;
    public final EObject ruleSetDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_iteration_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:399:2: ( ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' ) )
            // InternalLea.g:400:2: ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' )
            {
            // InternalLea.g:400:2: ( () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']' )
            // InternalLea.g:401:3: () otherlv_1= '[' ( (lv_iteration_2_0= ruleIteration ) )? otherlv_3= ']'
            {
            // InternalLea.g:401:3: ()
            // InternalLea.g:402:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetDefinitionAccess().getSetDefinitionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,20,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getSetDefinitionAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalLea.g:412:3: ( (lv_iteration_2_0= ruleIteration ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalLea.g:413:4: (lv_iteration_2_0= ruleIteration )
                    {
                    // InternalLea.g:413:4: (lv_iteration_2_0= ruleIteration )
                    // InternalLea.g:414:5: lv_iteration_2_0= ruleIteration
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
    // InternalLea.g:439:1: entryRuleIteration returns [EObject current=null] : iv_ruleIteration= ruleIteration EOF ;
    public final EObject entryRuleIteration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIteration = null;


        try {
            // InternalLea.g:439:50: (iv_ruleIteration= ruleIteration EOF )
            // InternalLea.g:440:2: iv_ruleIteration= ruleIteration EOF
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
    // InternalLea.g:446:1: ruleIteration returns [EObject current=null] : ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) ) ;
    public final EObject ruleIteration() throws RecognitionException {
        EObject current = null;

        Token lv_iterator_0_0=null;
        Token otherlv_1=null;
        Token lv_iterable_2_0=null;


        	enterRule();

        try {
            // InternalLea.g:452:2: ( ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) ) )
            // InternalLea.g:453:2: ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) )
            {
            // InternalLea.g:453:2: ( ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) ) )
            // InternalLea.g:454:3: ( (lv_iterator_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_iterable_2_0= RULE_ID ) )
            {
            // InternalLea.g:454:3: ( (lv_iterator_0_0= RULE_ID ) )
            // InternalLea.g:455:4: (lv_iterator_0_0= RULE_ID )
            {
            // InternalLea.g:455:4: (lv_iterator_0_0= RULE_ID )
            // InternalLea.g:456:5: lv_iterator_0_0= RULE_ID
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
            		
            // InternalLea.g:476:3: ( (lv_iterable_2_0= RULE_ID ) )
            // InternalLea.g:477:4: (lv_iterable_2_0= RULE_ID )
            {
            // InternalLea.g:477:4: (lv_iterable_2_0= RULE_ID )
            // InternalLea.g:478:5: lv_iterable_2_0= RULE_ID
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
    // InternalLea.g:498:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalLea.g:498:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalLea.g:499:2: iv_ruleAssignment= ruleAssignment EOF
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
    // InternalLea.g:505:1: ruleAssignment returns [EObject current=null] : (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_element_1_0=null;
        EObject lv_operation_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:511:2: ( (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) ) )
            // InternalLea.g:512:2: (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) )
            {
            // InternalLea.g:512:2: (otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) )
            // InternalLea.g:513:3: otherlv_0= '=' ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getAssignmentAccess().getEqualsSignKeyword_0());
            		
            // InternalLea.g:517:3: ( ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_ID) ) {
                int LA9_1 = input.LA(2);

                if ( ((LA9_1>=24 && LA9_1<=25)) ) {
                    alt9=2;
                }
                else if ( (LA9_1==EOF||LA9_1==11||LA9_1==14||(LA9_1>=17 && LA9_1<=19)) ) {
                    alt9=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalLea.g:518:4: ( (lv_element_1_0= RULE_ID ) )
                    {
                    // InternalLea.g:518:4: ( (lv_element_1_0= RULE_ID ) )
                    // InternalLea.g:519:5: (lv_element_1_0= RULE_ID )
                    {
                    // InternalLea.g:519:5: (lv_element_1_0= RULE_ID )
                    // InternalLea.g:520:6: lv_element_1_0= RULE_ID
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
                    // InternalLea.g:537:4: ( (lv_operation_2_0= ruleOperation ) )
                    {
                    // InternalLea.g:537:4: ( (lv_operation_2_0= ruleOperation ) )
                    // InternalLea.g:538:5: (lv_operation_2_0= ruleOperation )
                    {
                    // InternalLea.g:538:5: (lv_operation_2_0= ruleOperation )
                    // InternalLea.g:539:6: lv_operation_2_0= ruleOperation
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
    // InternalLea.g:561:1: entryRuleOperation returns [EObject current=null] : iv_ruleOperation= ruleOperation EOF ;
    public final EObject entryRuleOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation = null;


        try {
            // InternalLea.g:561:50: (iv_ruleOperation= ruleOperation EOF )
            // InternalLea.g:562:2: iv_ruleOperation= ruleOperation EOF
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
    // InternalLea.g:568:1: ruleOperation returns [EObject current=null] : ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? ) ;
    public final EObject ruleOperation() throws RecognitionException {
        EObject current = null;

        Token lv_element_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_call_2_0 = null;

        EObject lv_call_4_0 = null;



        	enterRule();

        try {
            // InternalLea.g:574:2: ( ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? ) )
            // InternalLea.g:575:2: ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? )
            {
            // InternalLea.g:575:2: ( ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )? )
            // InternalLea.g:576:3: ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )? ( (lv_call_2_0= ruleCall ) ) (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )?
            {
            // InternalLea.g:576:3: ( ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==24) ) {
                    alt10=1;
                }
            }
            switch (alt10) {
                case 1 :
                    // InternalLea.g:577:4: ( (lv_element_0_0= RULE_ID ) ) otherlv_1= '.'
                    {
                    // InternalLea.g:577:4: ( (lv_element_0_0= RULE_ID ) )
                    // InternalLea.g:578:5: (lv_element_0_0= RULE_ID )
                    {
                    // InternalLea.g:578:5: (lv_element_0_0= RULE_ID )
                    // InternalLea.g:579:6: lv_element_0_0= RULE_ID
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

            // InternalLea.g:600:3: ( (lv_call_2_0= ruleCall ) )
            // InternalLea.g:601:4: (lv_call_2_0= ruleCall )
            {
            // InternalLea.g:601:4: (lv_call_2_0= ruleCall )
            // InternalLea.g:602:5: lv_call_2_0= ruleCall
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

            // InternalLea.g:619:3: (otherlv_3= '.' ( (lv_call_4_0= ruleCall ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==24) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLea.g:620:4: otherlv_3= '.' ( (lv_call_4_0= ruleCall ) )
                    {
                    otherlv_3=(Token)match(input,24,FOLLOW_4); 

                    				newLeafNode(otherlv_3, grammarAccess.getOperationAccess().getFullStopKeyword_2_0());
                    			
                    // InternalLea.g:624:4: ( (lv_call_4_0= ruleCall ) )
                    // InternalLea.g:625:5: (lv_call_4_0= ruleCall )
                    {
                    // InternalLea.g:625:5: (lv_call_4_0= ruleCall )
                    // InternalLea.g:626:6: lv_call_4_0= ruleCall
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
    // InternalLea.g:648:1: entryRuleCall returns [EObject current=null] : iv_ruleCall= ruleCall EOF ;
    public final EObject entryRuleCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCall = null;


        try {
            // InternalLea.g:648:45: (iv_ruleCall= ruleCall EOF )
            // InternalLea.g:649:2: iv_ruleCall= ruleCall EOF
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
    // InternalLea.g:655:1: ruleCall returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' ) ;
    public final EObject ruleCall() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_parameters_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:661:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' ) )
            // InternalLea.g:662:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' )
            {
            // InternalLea.g:662:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')' )
            // InternalLea.g:663:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleParameterList ) )? otherlv_3= ')'
            {
            // InternalLea.g:663:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalLea.g:664:4: (lv_name_0_0= RULE_ID )
            {
            // InternalLea.g:664:4: (lv_name_0_0= RULE_ID )
            // InternalLea.g:665:5: lv_name_0_0= RULE_ID
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
            		
            // InternalLea.g:685:3: ( (lv_parameters_2_0= ruleParameterList ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_STRING)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalLea.g:686:4: (lv_parameters_2_0= ruleParameterList )
                    {
                    // InternalLea.g:686:4: (lv_parameters_2_0= ruleParameterList )
                    // InternalLea.g:687:5: lv_parameters_2_0= ruleParameterList
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
    // InternalLea.g:712:1: entryRuleParameterList returns [EObject current=null] : iv_ruleParameterList= ruleParameterList EOF ;
    public final EObject entryRuleParameterList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterList = null;


        try {
            // InternalLea.g:712:54: (iv_ruleParameterList= ruleParameterList EOF )
            // InternalLea.g:713:2: iv_ruleParameterList= ruleParameterList EOF
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
    // InternalLea.g:719:1: ruleParameterList returns [EObject current=null] : ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* ) ;
    public final EObject ruleParameterList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_parameterList_0_0 = null;

        EObject lv_parameterList_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:725:2: ( ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* ) )
            // InternalLea.g:726:2: ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* )
            {
            // InternalLea.g:726:2: ( ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )* )
            // InternalLea.g:727:3: ( (lv_parameterList_0_0= ruleParameter ) ) (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )*
            {
            // InternalLea.g:727:3: ( (lv_parameterList_0_0= ruleParameter ) )
            // InternalLea.g:728:4: (lv_parameterList_0_0= ruleParameter )
            {
            // InternalLea.g:728:4: (lv_parameterList_0_0= ruleParameter )
            // InternalLea.g:729:5: lv_parameterList_0_0= ruleParameter
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

            // InternalLea.g:746:3: (otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==13) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalLea.g:747:4: otherlv_1= ',' ( (lv_parameterList_2_0= ruleParameter ) )
            	    {
            	    otherlv_1=(Token)match(input,13,FOLLOW_21); 

            	    				newLeafNode(otherlv_1, grammarAccess.getParameterListAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalLea.g:751:4: ( (lv_parameterList_2_0= ruleParameter ) )
            	    // InternalLea.g:752:5: (lv_parameterList_2_0= ruleParameter )
            	    {
            	    // InternalLea.g:752:5: (lv_parameterList_2_0= ruleParameter )
            	    // InternalLea.g:753:6: lv_parameterList_2_0= ruleParameter
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
            	    break loop13;
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
    // InternalLea.g:775:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalLea.g:775:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalLea.g:776:2: iv_ruleParameter= ruleParameter EOF
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
    // InternalLea.g:782:1: ruleParameter returns [EObject current=null] : ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token lv_text_0_0=null;
        Token lv_element_1_0=null;
        EObject lv_operation_2_0 = null;



        	enterRule();

        try {
            // InternalLea.g:788:2: ( ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) ) )
            // InternalLea.g:789:2: ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            {
            // InternalLea.g:789:2: ( ( (lv_text_0_0= RULE_STRING ) ) | ( (lv_element_1_0= RULE_ID ) ) | ( (lv_operation_2_0= ruleOperation ) ) )
            int alt14=3;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_STRING) ) {
                alt14=1;
            }
            else if ( (LA14_0==RULE_ID) ) {
                int LA14_2 = input.LA(2);

                if ( (LA14_2==EOF||LA14_2==13||LA14_2==26) ) {
                    alt14=2;
                }
                else if ( ((LA14_2>=24 && LA14_2<=25)) ) {
                    alt14=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 2, input);

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
                    // InternalLea.g:790:3: ( (lv_text_0_0= RULE_STRING ) )
                    {
                    // InternalLea.g:790:3: ( (lv_text_0_0= RULE_STRING ) )
                    // InternalLea.g:791:4: (lv_text_0_0= RULE_STRING )
                    {
                    // InternalLea.g:791:4: (lv_text_0_0= RULE_STRING )
                    // InternalLea.g:792:5: lv_text_0_0= RULE_STRING
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
                    // InternalLea.g:809:3: ( (lv_element_1_0= RULE_ID ) )
                    {
                    // InternalLea.g:809:3: ( (lv_element_1_0= RULE_ID ) )
                    // InternalLea.g:810:4: (lv_element_1_0= RULE_ID )
                    {
                    // InternalLea.g:810:4: (lv_element_1_0= RULE_ID )
                    // InternalLea.g:811:5: lv_element_1_0= RULE_ID
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
                    // InternalLea.g:828:3: ( (lv_operation_2_0= ruleOperation ) )
                    {
                    // InternalLea.g:828:3: ( (lv_operation_2_0= ruleOperation ) )
                    // InternalLea.g:829:4: (lv_operation_2_0= ruleOperation )
                    {
                    // InternalLea.g:829:4: (lv_operation_2_0= ruleOperation )
                    // InternalLea.g:830:5: lv_operation_2_0= ruleOperation
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
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000000000E0802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
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