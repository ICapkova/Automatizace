package cz.czechitas.automation;

import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

/**
 * New Application specific selenium actions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
final class ApplicationDetail {

    private final ElementFinder elementFinder;

    ApplicationDetail(ElementFinder elementFinder) {
        this.elementFinder = Objects.requireNonNull(elementFinder);
    }

    void selectTerm(String term) {
        var dateElement = elementFinder.findByXPath("//button[@data-id='term_id']");

        dateElement.click();
        var option = elementFinder.findByXPath(
                "//div[starts-with(@id,'bs-select')]//span[contains(text(), '" + term + "')]"
        );
        option.click();
    }

    void selectCategory(String text) {
        var textElement = elementFinder.findByXPath("/html/body/div[1]/div/div/div/div/form/table/tbody/tr[1]/td[2]/div/button");

        textElement.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/form/table/tbody/tr[1]/td[2]/div/button/div/div/div"
        );
        option.click();
    }

    void insertStudentFirstName(String firstName) {
        var firstNameInput = elementFinder.findByXPath("//*[@id='forename']");
        firstNameInput.sendKeys(firstName);
    }

    void insertStudentLastName(String lastname) {
        var lastNameInput = elementFinder.findByXPath("//*[@id='surname']");
        lastNameInput.sendKeys(lastname);
    }

    void insertCancellationReason(String CancellationReason) {
        var cancellationReasonInput = elementFinder.findByXPath("//*[@id='canceled']");
        cancellationReasonInput.sendKeys(CancellationReason);
    }

    void insertBirthdate(String birthdate) {
        var birthDate = elementFinder.findByXPath("//*[@id='birthday']");
        birthDate.sendKeys(birthdate);
    }

    void insertNote(String note) {
        var noteInput = elementFinder.findByXPath("//*[@id='note']");
        noteInput.sendKeys(note);
    }

    void insertAmount(String note) {
        var noteInput = elementFinder.findByXPath("//*[@id=\"price\"]");
        noteInput.sendKeys(note);
    }

    void clickAcceptTermsCheckbox() {
        var approvalCheckbox = elementFinder.findByXPath(
                "//label[@for='terms_conditions']");
        approvalCheckbox.click();
    }

    void clickHealthDisabilityCheckbox() {
        var healthDisabilityCheckbox = elementFinder.findByXPath("/html/body/div/div/div/div/div/form/table/tbody/tr[9]/td[2]/span/label");
        healthDisabilityCheckbox.click();
    }

    void insertHealthDisabilityNote(String note) {
        var healthDisabilityNoteInput = elementFinder.findByXPath("//*[@id=\"restrictions\"]");
        healthDisabilityNoteInput.sendKeys(note);
    }

    void clickCreateApplicationButton() {
        var createButton = elementFinder.findByXPath(
                "/html/body/div/div/div/div/div/div[1]/div/a");
        createButton.click();
    }

    void clickFinishApplicationButton() {
        var createButton = elementFinder.findByXPath(
                "/html/body/div/div/div/div/div/form/table/tbody/tr[11]/td[2]/input");
        createButton.click();
    }

    void clickClosePaymentEditButton() {
        var createButton = elementFinder.findByXPath(
                "//*[@id=\"studentPaymentHistory\"]/div/div/div[1]/button/span");
        createButton.click();
    }

    void clickCreateTermButton() {
        var createTermButton = elementFinder.findByXPath(
                "/html/body/div/div/div/div/div/div[1]/div/a");
        createTermButton.click();
    }

    void selectCashPaymentMethod() {
        var inCashRadioButton = elementFinder.findByXPath(
                "//label[@for='payment_cash']");
        inCashRadioButton.click();
    }

    void selectBankTransferPaymentMethod() {
        var toBankAccountButton = elementFinder.findByXPath(
                "//label[@for='payment_transfer']");
        toBankAccountButton.click();
    }

    void selectSlipPaymentMethod() {
        var slipButton = elementFinder.findByXPath(
                "//label[@for='payment_postal_order']");
        slipButton.click();
    }

    void selectFKSPPaymentMethod() {
        var fkspButton = elementFinder.findByXPath(
                "//label[@for='payment_fksp']");
        fkspButton.click();
    }

    void clickEditApplicationButton() {
        var editButton = elementFinder.findByXPath(
                "//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[5]/div/a[2]/i");
        editButton.click();
    }

    void clickApplicationDetailButton() {
        var editButton = elementFinder.findByXPath(
                "//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[5]/div/a[1]/i");
        editButton.click();
    }

    void clickOrderDetailButton() {
        var editButton = elementFinder.findByXPath(
                "//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[6]/div[1]/a[1]/i");
        editButton.click();
    }
    void clickPaymentManagButton() {
        var editButton = elementFinder.findByXPath(
                "/html/body/div[1]/div/div[1]/div/div/div[1]/div[1]/button");
        editButton.click();
    }

    void clickPaymentHistoryButton() {
        var editButton = elementFinder.findByXPath(
                "/html/body/div[1]/div/div[1]/div/div/div[1]/div[1]/button[1]");
        editButton.click();
    }

    void clickCancelApplicationButton() {
        var cancelButton = elementFinder.findByXPath(
                "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/button[2]");
        cancelButton.click();
    }

    void clickApplicationCancelledButton() {
        var cancelButton = elementFinder.findByXPath(
                "//*[@id=\"studentCancel\"]/div/div/div[2]/form/div[1]/span/label");
        cancelButton.click();
    }

    void clickConfirmCancellationButton() {
        var confirmButton = elementFinder.findByXPath(
                "//*[@id=\"studentCancel\"]/div/div/div[2]/form/input[4]");
        confirmButton.click();
    }

    void clickInsertPaymentButton() {
        var editButton = elementFinder.findByXPath(
                "//*[@id=\"addStudentPayment\"]/div/div/div[2]/form/table/tbody/tr[6]/td/input");
        editButton.click();
    }

    void clickPaymentButton() {
        var editButton = elementFinder.findByXPath(
                "/html/body/div[1]/div/div[1]/div/div/div[1]/div[1]/button");
        editButton.click();
    }
}