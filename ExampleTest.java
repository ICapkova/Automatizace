package cz.czechitas.automation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;

/**
 * Example test class for functionality showcase
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class ExampleTest extends TestRunner {

    @Test
    void contactsPageUrlTest() {
        browser.headerMenu.goToContactsSection();
        asserter.generalSection.checkPageUrl("www.czechitas.cz");
    }

    @Test
    void successfulLoginTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("da-app.admin@czechitas.cz");
        browser.loginSection.insertPassword("Czechitas123");
        browser.loginSection.clickLoginButton();
        asserter.applicationSection.checkIsLoggedIn();
    }

    // paramertized test - find out what is wrong with this test
    @ParameterizedTest()
    @ValueSource(strings = {"123456789", "ASDFBVC", "123"})
    void icoFieldTest(String icoValue) {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(icoValue);
    }

    void login(String login, String password) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(login);
        browser.loginSection.insertPassword(password);
        browser.loginSection.clickLoginButton();
    }

    //DATBP25I-19, Master admin/admin access to data
    // As an admin or master admin I need to have access to admin section of Czechitas application
    // so I can edit orders, applications, create terms, categories and news or maintain exports.

    //Jako Master upravím objednávku, změny uložím, ověřím, že změna proběhla:
    @Test
    void accesToDataMAChangeOrder() {
        login ("ikeitas.master@gmail.com", "Pejsek1");
        browser.waitFor(2);

        browser.internalMenu.goToOrdersSection();
        browser.waitFor(2);
        browser.applicationSection.clickEditFirstApplicationButton();
        browser.waitFor(2);
        browser.orderDetailSection.clearContactPersonNameAndSurname();
        browser.orderDetailSection.insertContactPersonNameAndSurname("Iva","Capkova");

        browser.orderDetailSection.saveOrderChanges();
        browser.waitFor(2);
        browser.applicationDetailsSection.clickOrderDetailButton();
        browser.waitFor(2);
        asserter.applicationDetailSection.checkContactName("Iva Capkova");

}

//Tady ověřuji, že může MA do všech položek Admin menu, a že je může upravit/vytvořit.
    @Test
    void accesToAnyAdminSectionMA() {
        login ("ikeitas.master@gmail.com", "Pejsek1");
        browser.waitFor(2);

        browser.internalMenu.goToOrdersSection();
        browser.waitFor(2);
        browser.applicationSection.clickEditFirstApplicationButton();

        browser.internalMenu.goToTermsSection();
        browser.waitFor(2);
        browser.applicationSection.clickEditFirstApplicationButton();
        browser.waitFor(2);

        browser.internalMenu.goToApplicationsSection();
        browser.waitFor(2);
        browser.applicationSection.clickEditFirstApplicationButton();
        browser.waitFor(2);

        browser.internalMenu.goToCategoriesSection();
        browser.waitFor(2);
        browser.applicationSection.clickEditFirstApplicationButton();
        browser.waitFor(2);

        browser.internalMenu.goToNewsSection();
        browser.waitFor(2);
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.waitFor(2);

        browser.internalMenu.goToExportsSection();
        browser.waitFor(2);
        browser.applicationSection.clickExportListOfChildrenButton();
        browser.waitFor(4);


}
//Totéž by bylo pro admina, jen změna dat v loginu
    @Test
    void accesToAnyAdminSectionAdmin() {
        login ("da-app.admin@czechitas.cz", "Czechitas123");
}

    //US9 - Manage payments - ověřuji, že MA může dostat do Správy plateb v jím vytvořené přihlášce,
    // vložit přijatou platbu, podívat se do historie plateb
    @Test
    void paymentManagementByMA() {
        login ("ikeitas.master@gmail.com", "Pejsek1");
        browser.waitFor(2);

        // vytvořím novou přihlášku pro dítě s náhodným příjmením;

        browser.internalMenu.goToApplicationsSection();
        browser.applicationDetailsSection.clickCreateApplicationButton();
        browser.waitFor(2);
        browser.applicationDetailsSection.selectTerm("28.11.2025");
        browser.applicationDetailsSection.insertStudentFirstName("Petra");
        var nahodnePrijmeni1 = browser.generateRandomName(7);
        browser.applicationDetailsSection.insertStudentLastName(nahodnePrijmeni1);
        browser.applicationDetailsSection.insertBirthdate("23.05.2013");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickFinishApplicationButton();
        browser.waitFor(2);

        browser.internalMenu.goToApplicationsSection();
        browser.applicationSection.search(nahodnePrijmeni1);
        browser.waitFor(2);
        asserter.applicationSection.checkNumberOfApplications(1);

        browser.waitFor(2);
        browser.applicationDetailsSection.clickApplicationDetailButton();
        browser.waitFor(2);
        browser.applicationDetailsSection.clickPaymentManagButton();
        browser.applicationDetailsSection.selectBankTransferPaymentMethod();
        browser.generateRandomNumber();
        browser.waitFor(2);

        int randomNumber = browser.generateRandomNumber();

        browser.applicationDetailsSection.insertAmount(String.valueOf(randomNumber));
        browser.applicationDetailsSection.clickInsertPaymentButton();
        browser.waitFor(2);
        browser.applicationDetailsSection.clickPaymentHistoryButton();

        //ověřím,že v historii plateb se vyskytují všechny požadované položky:
        asserter.applicationSection.checkColumnPaymentExists("Datum");
        asserter.applicationSection.checkColumnPaymentExists("Operace");
        asserter.applicationSection.checkColumnPaymentExists("Hodnota");
        asserter.applicationSection.checkColumnPaymentExists("Poznámka");
        browser.waitFor(8);

        browser.applicationDetailsSection.clickClosePaymentEditButton();

        //a ještě jsem si zkusila zrušit přihlášku. Když jsem dala nejdřív vyplnit a pak kliknout
        // na potvrzení, vepsaný text zmizel a test padal. Proto tam mám dvě kliknutí
        // na clickConfirmCancelllationButton - poradila mi AI, že to tak mám udělat.
        browser.applicationDetailsSection.clickCancelApplicationButton();
        browser.applicationDetailsSection.clickApplicationCancelledButton();
        browser.applicationDetailsSection.clickConfirmCancellationButton();
        browser.applicationDetailsSection.insertCancellationReason("Nemoc");
        browser.waitFor(2);
        browser.applicationDetailsSection.clickConfirmCancellationButton();

        browser.internalMenu.goToApplicationsSection();
        browser.applicationSection.search(nahodnePrijmeni1);
        browser.waitFor(2);

        browser.applicationDetailsSection.clickApplicationDetailButton();
        asserter.applicationDetailSection.checkApplicationCanceled("zrušena");

    }
}