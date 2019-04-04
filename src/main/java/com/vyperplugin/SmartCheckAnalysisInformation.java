package com.vyperplugin;

import org.antlr.v4.runtime.ParserRuleContext;
import ru.smartdec.smartcheck.TreeAnalysis;

//
// represent analysis information from smartCheck Tool
//
final public class SmartCheckAnalysisInformation {
    public TreeAnalysis.Info info;
    public ParserRuleContext context;
    public SmartCheckAnalysisInformation(TreeAnalysis.Info info, ParserRuleContext context) {
        this.info = info;
        this.context = context;
    }
    public SmartCheckAnalysisInformation() {

    }
}