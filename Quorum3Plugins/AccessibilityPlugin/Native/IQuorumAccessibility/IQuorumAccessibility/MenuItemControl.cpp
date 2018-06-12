#include <windows.h>
#include <UIAutomation.h>

#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "MenuBarControl.h"

// For error reporting
#include <string>
#include <iostream>


MenuItemControl::MenuItemControl(_In_ WCHAR* menuItemName, _In_ WCHAR* menuItemShortcut, _In_ ULONG uniqueId, _In_ MenuItemControl* parentMenuItem, _In_ MenuBarControl* parentMenuBar)
	: m_shortcut(menuItemShortcut), m_pParentMenuBar(parentMenuBar), m_pParentMenuItem(parentMenuItem),
	  m_pMenuItemProvider(NULL), m_uniqueId(uniqueId)
{
	this->SetName(menuItemName);
	this->SetDescription(L"");
	this->m_ControlHWND = NULL;
}

MenuItemControl::~MenuItemControl()
{
}

MenuBarControl * MenuItemControl::GetParentMenuBar()
{
	return m_pParentMenuBar;
}

void MenuItemControl::SetParentMenuBar(_In_ MenuBarControl * menuBar)
{
	m_pParentMenuBar = menuBar;
}

MenuItemControl * MenuItemControl::GetParentMenuItem()
{
	return m_pParentMenuItem;
}

MenuItemProvider * MenuItemControl::GetMenuItemProvider()
{
	if (m_pMenuItemProvider == NULL)
	{
		m_pMenuItemProvider = new MenuItemProvider(this);
		UiaRaiseAutomationEvent(m_pMenuItemProvider, UIA_Window_WindowOpenedEventId);
	}
	return m_pMenuItemProvider;
}

MenuControl * MenuItemControl::GetMenuControl()
{
	MenuControl* menuControl = GetParentMenuItem();
	if (menuControl == NULL)
		menuControl = GetParentMenuBar();

	return menuControl;
}

WCHAR * MenuItemControl::GetShortcut()
{
	return m_shortcut;
}

ULONG MenuItemControl::GetId()
{
	return m_uniqueId;
}

int MenuItemControl::GetMenuItemIndex()
{
	MenuControl* pMenuControl = GetMenuControl();
		
	for (int i = 0; i < pMenuControl->GetCount(); i++)
	{
		MENUITEM_ITERATOR menuItem = pMenuControl->GetMenuItemAt(i);
		MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(*menuItem);
		if (pMenuItem == this)
		{
			m_myIndex = i;
			break;
		}
	}
	
	return m_myIndex;
	
}

bool MenuItemControl::HasFocus()
{
	return (GetParentMenuBar()->GetSelectedMenuItem() == this) && (GetParentMenuBar()->HasFocus());
}

void MenuItemControl::SetControlFocus(bool focused)
{
}



