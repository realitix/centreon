import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

def config = TestDataFactory.findTestData('Configuration')

//I use the autologin url
WebUI.openBrowser(config.getValue('url', 1) + 'index.php?autologin=1&useralias=' + userName + '&token=' + autologin)

WebUI.waitForPageLoad(3)

//Refuse to use the new menu
if(WebUI.verifyElementPresent(findTestObject('General/button_Disable new feature'), 1, FailureHandling.OPTIONAL)){
	WebUI.click(findTestObject('General/button_Disable new feature'))
	
	WebUI.waitForPageLoad(3)
}

//I verify that the new url is correct 
url = WebUI.getUrl()

WebUI.verifyMatch(url, config.getValue('url', 1) + 'main.php', false)

WebUI.click(findTestObject('Old menu/a_Logout'))

WebUI.closeBrowser()
