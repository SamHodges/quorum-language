#pragma once

#include "jni.h"
#include <UIAutomation.h>
#include <iostream>
#include <string>

#ifndef LOG
#define LOG 0
#endif

class WindowRoot;
WindowRoot* GetMainWindowRoot();

JNIEnv* GetJNIEnv();

WCHAR* CreateWideStringFromUTF8Win32(const char* source);

/**************************************************************
* Static Global Variables to cache Java Class and Method IDs
*
**************************************************************/
void log(std::string s);

struct JClass_AccessibilityManager
{
	jclass me;
	jmethodID WaitForUpdate;
	jmethodID GetTreeSelectionPointer;
	jmethodID GetTabPaneSelectionPointer;
	jmethodID GetSpreadsheetSelectionPointer;
	jmethodID SetTabSelection;

	// Used for cells and Spreadsheets/TreeTable.
	jmethodID GetCellColumnIndex;
	jmethodID GetCellRowIndex;
	jmethodID GetCellText;

	// Used for Lists.
	jmethodID GetListItemText;

	// Used to calculate bounding rectangles.
	jmethodID GetBoundingRectangle;
};
struct JClass_TextBox
{
	jclass me;
	jmethodID GetCaretLine;
	jmethodID GetCaretPosition;
	jmethodID GetIndexOfLine;
	jmethodID GetText;
	jmethodID GetCurrentLineText;
	jmethodID GetSelection;
};
struct JClass_TextBoxSelection
{
	jclass me;
	jmethodID IsEmpty;
	jmethodID GetStartIndex;
	jmethodID GetEndIndex;
};
struct JClass_TextField
{
	jclass me;
	jmethodID GetCaretPosition;
	jmethodID GetText;
	jmethodID GetSelection;
	jmethodID GetSize;
};
struct JClass_TextFieldSelection
{
	jclass me;
	jmethodID IsEmpty;
	jmethodID GetStartIndex;
	jmethodID GetEndIndex;
};
struct JClass_Button
{
	jclass me;
	jmethodID Activate;
};
struct JClass_ToggleButton
{
	jclass me;
	jmethodID SetToggleState;
	jmethodID GetToggleState;
};
struct JClass_TabPane
{
	jclass me;
};
struct JClass_Tab
{
	jclass me;
};
struct JClass_ToolBar
{
	jclass me;
};
struct JClass_Dialog
{
	jclass me;
};
struct JClass_List
{
	jclass me;
};
struct JClass_ListItem
{
	jclass me;
};
struct JClass_Spreadsheet
{
	jclass me;
};
struct JClass_TreeTable
{
	jclass me;
};
struct JClass_Cell
{
	jclass me;
};
struct JClass_Item
{
	jclass me;
	jmethodID GetName;
	jmethodID GetDescription;
};

extern JClass_AccessibilityManager JavaClass_AccessibilityManager;
extern JClass_TextBox JavaClass_TextBox;
extern JClass_TextBoxSelection JavaClass_TextBoxSelection;
extern JClass_TextField JavaClass_TextField;
extern JClass_TextFieldSelection JavaClass_TextFieldSelection;
extern JClass_Button JavaClass_Button;
extern JClass_ToggleButton JavaClass_ToggleButton;
extern JClass_Item JavaClass_Item;
extern JClass_ToolBar JavaClass_ToolBar;
extern JClass_Dialog JavaClass_Dialog;
extern JClass_Dialog JavaClass_List;
extern JClass_Dialog JavaClass_Spreadsheet;
extern JClass_Dialog JavaClass_TreeTable;
extern JClass_Dialog JavaClass_Cell;
extern JClass_Dialog JavaClass_ListItem;
