/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.tests.compiler.other;

import org.quorum.execution.RunResult;
import java.io.File;
import org.quorum.execution.ExpressionValue;
import org.quorum.tests.compiler.CompilerTestSuite;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Unit tests for cases that cannot be classified or are difficult to
 * classify are defined here.
 *
 * @author Kim Slattery
 */
public class OtherTester {

    private QuorumVirtualMachine vm;
    
    public OtherTester() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        CompilerTestSuite.forceSetup();
        vm = CompilerTestSuite.getVirtualMachine();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_fail_AssignmentToUndeclaredVariable_run(){
        String directory = CompilerTestSuite.OTHER + CompilerTestSuite.FAIL;

        try {
            CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(directory + "AssignmentToUndeclaredVariable.quorum"));
        }
        catch(Exception e) {
            fail();
        }
        if (vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
    }

    @Test
    public void test_pass_ThisSet_execute(){
        File[] files = new File[1];
        files[0] = CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ThisSet.quorum");

        CompilerTestSuite.build(files);

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue("result");
        boolean a = variableValue.getResult().boolean_value;
        if(a == true) {
            fail();
        }
    }
    
    @Test
    public void test_not_not() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NotNot.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue trueAVal = vm.getDataEnvironment().getVariableValue("trueA");
        ExpressionValue trueBVal = vm.getDataEnvironment().getVariableValue("trueB");
        boolean trueA = trueAVal.getResult().boolean_value;
        boolean trueB = trueBVal.getResult().boolean_value;
        
        if (!trueA && !trueB) {
            fail();
        }
    }
    
    @Test
    public void test_not_not_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "NotNot.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("true"));
        assert(r.getLine(1).equals("true"));
    }
    
    @Test
    public void test_parameters_with_same_names_as_ivars() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "ParametersWithSameNameAsIVars.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue iVarVal = vm.getDataEnvironment().getVariableValue("i");
        ExpressionValue vVarVal = vm.getDataEnvironment().getVariableValue("v");
        ExpressionValue iValueVarVal = vm.getDataEnvironment().getVariableValue("i_value");
        ExpressionValue iMeValueVarVal = vm.getDataEnvironment().getVariableValue("i_me_value");
        int i = iVarVal.getResult().integer;
        int v = vVarVal.getResult().integer;
        int i_value = iValueVarVal.getResult().integer;
        int i_me_value = iMeValueVarVal.getResult().integer;
        
        if (i != 0 || v != 0 || i_value != 0 || i_me_value != 0) {
            fail();
        }
    }
    
    @Test
    public void test_pass_recursion() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "Recursion.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue fibResultAVal = vm.getDataEnvironment().getVariableValue("fibResultA");
        ExpressionValue fibResultBVal = vm.getDataEnvironment().getVariableValue("fibResultB");
        int fibResultA = fibResultAVal.getResult().integer;
        int fibResultB = fibResultBVal.getResult().integer;
        
        if (fibResultA != 8 || fibResultB != 8) {
            fail();
        }
    }
    
    
    @Test
    public void test_pass_recursion_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "Recursion.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("8"));
        assert(r.getLine(1).equals("8"));
    }
    
    @Test
    public void test_pass_print_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "Print.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
    }
        
    @Test
    public void test_pass_me_assignment_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "MeAssignmentAndPrint.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("10.0"));
    }

    @Test
    public void test_cast_with_addition() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastWithAddition.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue noteVal = vm.getDataEnvironment().getVariableValue("note");
        int note = noteVal.getResult().integer;
        
        if (note != 72) {
            fail();
        }
    }
    
    @Test
    public void test_cast_with_addition_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastWithAddition.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("72"));
    }
    
    @Test
    public void test_cast_function_result_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResult.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
        int k = val.getResult().integer;
        
        if (k != 5) {
            fail();
        }
    }
    
    @Test
    public void test_cast_function_result_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResult.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_cast_function_result_autobox_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResultAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
        int k = val.getResult().integer;
        
        if (k != 5) {
            fail();
        }
    }
    
    @Test
    public void test_cast_function_result_autobox_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastFunctionResultAutoBox.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_cast_nested_function_result_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastNestedFunctionResult.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
        int k = val.getResult().integer;
        
        if (k != 5) {
            fail();
        }
    }
    
    @Test
    public void test_cast_nested_function_result_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastNestedFunctionResult.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
    
    @Test
    public void test_cast_expression_execute() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastExpression.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }
        vm.blockRun();

        ExpressionValue val = vm.getDataEnvironment().getVariableValue("k");
        int k = val.getResult().integer;
        
        if (k != 5) {
            fail();
        }
    }
    
    @Test
    public void test_cast_expression_bytecode() {
        CompilerTestSuite.build(CompilerTestSuite.getQuorumFile(CompilerTestSuite.OTHER + CompilerTestSuite.PASS + "CastExpression.quorum"));

        if (!vm.getCompilerErrors().isCompilationErrorFree()){
            fail();
        }

        RunResult r = CompilerTestSuite.runQuorumFile();
        if (!r.isSuccessful())
            fail();
        
        assert(r.getLine(0).equals("5"));
    }
}
