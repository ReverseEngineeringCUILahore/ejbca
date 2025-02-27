package org.ejbca.webtest.scenario;

import org.apache.commons.lang.StringUtils;
import org.cesecore.authorization.AuthorizationDeniedException;
import org.ejbca.webtest.WebTestBase;
import org.ejbca.webtest.helper.ApprovalProfilesHelper;
import org.ejbca.webtest.helper.CaHelper;
import org.ejbca.webtest.helper.CertificateProfileHelper;
import org.ejbca.webtest.helper.EndEntityProfileHelper;
import org.ejbca.webtest.helper.RaWebHelper;
import org.ejbca.webtest.helper.ValidatorsHelper;
import org.ejbca.webtest.utils.GetResourceDir;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import java.util.Calendar;
import java.util.Date;

/**
 * Asserts whether the blacklist validator permits a site based on the
 * blacklist.txt file using Exact Match.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EcaQa201_PositiveBlacklistExactMatch extends WebTestBase {

    // Helpers
    private static ValidatorsHelper validatorsHelper;
    private static CaHelper caHelper;
    private static ApprovalProfilesHelper approvalProfilesHelperDefault;
    private static CertificateProfileHelper certificateProfileHelper;
    private static EndEntityProfileHelper eeProfileHelper;
    private static RaWebHelper raWebHelper;

    // Test Data
    private static class TestData {
        private static final String VALIDATOR_NAME = "EcaQa201C_Blacklist";
        private static final String VALIDATOR_BLACKLIST_FILENAME = GetResourceDir.getResourceFolder() + "/Blacklist.txt";
        private static final String VALIDATOR_BLACKLIST_SITE = "www.yahoo.com";
        private static final String VALIDATOR_PERFORM_TYPE = "Exact match";
        private static final String CA_NAME = "EcaQa201C_CA";
        private static final String CA_VALIDITY = "1y";
        private static final String APPROVAL_PROFILE_NAME = "EcaQa201C_ApprovalProfile";
        private static final String APPROVAL_PROFILE_TYPE_PARTITIONED_APPROVAL = "Partitioned Approval";
        private static final String CERTIFICATE_PROFILE_NAME = "EcaQa201C_CertificateProfile";
        private static final String ROLE_NAME = "Super Administrator Role";
        private static final String ENTITY_NAME = "EcaQa201C_EntityProfile";
        static final String[] CERTIFICATE_REQUEST_PEM = new String[]{"-----BEGIN CERTIFICATE REQUEST-----", "MIICZzCCAU8CAQAwIjELMAkGA1UEBhMCVVMxEzARBgNVBAMMClJlc3RyaWN0Q04w", "ggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDwyIsyw3HB+8yxOF9BOfjG", "zLoQIX7sLg1lXk1miLyU6wYmuLnZfZrr4pjZLyEr2iP92IE97DeK/8y2827qctPM", "y4axmczlRTrEZKI/bVXnLOrQNw1dE+OVHiVoRFa5i4TS/qfhNA/Gy/eKpzxm8LT7", "+folAu92HwbQ5H8fWQ/l+ysjTheLMyUDaK83+NvYAL9Gfl29EN/TTrRzLKWoXrlB", "Ed7PT2oCBgrvF7pHsrry2O3yuuO2hoF5RQTo9BdBaGvzxGdweYTvdoLWfZm1zGI+", "CW0lprBdjagCC4XAcWi5OFcxjrRA9WA6Cu1q4Hn+eJEdCNHVvqss2rz6LOWjAQAr", "AgMBAAGgADANBgkqhkiG9w0BAQsFAAOCAQEA1JlwrFN4ihTZWICnWFb/kzcmvjcs", "0xeerNZQAEk2FJgj+mKVNrqCRWr2iaPpAeggH8wFoZIh7OvhmIZNmxScw4K5HhI9", "SZD+Z1Dgkj8+bLAQaxvw8sxXLdizcMNvbaXbzwbAN9OUkXPavBlik/b2JLafcEMM", "8IywJOtJMWemfmLgR7KAqDj5520wmXgAK6oAbbMqWUip1vz9oIisv53n2HFq2jzq", "a5d2WKBq5pJY19ztQ17HwlGTI8it4rlKYn8p2fDuqxLXiBsX8906E/cFRN5evhWt", "zdJ6yvdw3HQsoVAVi0GDHTs2E8zWFoYyP0byzKSSvkvQR363LQ0bik4cuQ==", "-----END CERTIFICATE REQUEST-----"};


    }

    @BeforeClass
    public static void init() {
        // super
        beforeClass(true, null);
        Date currentDate = new Date();
        Calendar oneMonthsFromNow = Calendar.getInstance();
        oneMonthsFromNow.setTime(currentDate);
        oneMonthsFromNow.add(Calendar.MONTH, 1);
        WebDriver webDriver = getWebDriver();

        // Init helpers
        validatorsHelper = new ValidatorsHelper(webDriver);
        caHelper = new CaHelper(webDriver);
        approvalProfilesHelperDefault = new ApprovalProfilesHelper(webDriver);
        certificateProfileHelper = new CertificateProfileHelper(webDriver);
        eeProfileHelper = new EndEntityProfileHelper(webDriver);
        raWebHelper = new RaWebHelper(webDriver);
    }

    @AfterClass
    public static void exit() throws AuthorizationDeniedException {
        // super
        afterClass();

        // Remove generated artifacts
        removeEndEntityProfileByName(TestData.ENTITY_NAME);
        removeCertificateProfileByName(TestData.CERTIFICATE_PROFILE_NAME);
        removeApprovalProfileByName(TestData.APPROVAL_PROFILE_NAME);
        removeCaAndCryptoToken(TestData.CA_NAME);
        removeValidatorByName(TestData.VALIDATOR_NAME);

    }

    @Test
    public void stepA_AddAValidator() {
        validatorsHelper.openPage(getAdminWebUrl());
        validatorsHelper.addValidator(TestData.VALIDATOR_NAME);
    }

    @Test
    public void stepB_EditValidatorWithBlacklist() {
        validatorsHelper.openEditValidatorPage(TestData.VALIDATOR_NAME);
        validatorsHelper.setValidatorType(ValidatorsHelper.ValidatorType.DOMAIN_BLACKLIST_VALIDATOR);
        validatorsHelper.setBlacklistPerformOption(TestData.VALIDATOR_PERFORM_TYPE);
        validatorsHelper.setBlacklistFile(TestData.VALIDATOR_BLACKLIST_FILENAME);
    }

    @Test
    public void stepC_SaveValidator() {
        validatorsHelper.saveValidator();
    }

    @Test
    public void stepD_EditValidatorSecondTime() {
        validatorsHelper.openEditValidatorPage(TestData.VALIDATOR_NAME);
        validatorsHelper.setBlackListSite(TestData.VALIDATOR_BLACKLIST_SITE);

        //Test to verify it returns a positive test result
        validatorsHelper.testBlacklistSite();
        validatorsHelper.assertBlackListResultsIsCorrect("Domain Blacklist Validator '" + TestData.VALIDATOR_NAME + "' permitted issuance of certificate.");
    }

    @Test
    public void stepE_SaveValidatorSecondTime() {
        validatorsHelper.saveValidator();
    }

    @Test
    public void stepF_AddNewCA() {
        caHelper.openPage(getAdminWebUrl());
        caHelper.addCa(TestData.CA_NAME);
        caHelper.checkEnforceUniquePublicKeys(false);
        caHelper.checkEnforceUniqueDN(false);
        caHelper.setValidity(TestData.CA_VALIDITY);
        caHelper.setOtherData(TestData.VALIDATOR_NAME);
    }

    @Test
    public void stepG_CreateCA() {
        caHelper.createCa();
        caHelper.assertExists(TestData.CA_NAME);
    }


    @Test
    public void stepH_AddApprovalProfile() {
        approvalProfilesHelperDefault.openPage(getAdminWebUrl());
        approvalProfilesHelperDefault.addApprovalProfile(TestData.APPROVAL_PROFILE_NAME);
        approvalProfilesHelperDefault.openEditApprovalProfilePage(TestData.APPROVAL_PROFILE_NAME);
        approvalProfilesHelperDefault.setApprovalProfileType(TestData.APPROVAL_PROFILE_TYPE_PARTITIONED_APPROVAL);
        approvalProfilesHelperDefault.setApprovalStepPartitionApprovePartitionRole(0, 0,
                TestData.ROLE_NAME);
    }

    @Test
    public void stepI_SaveApprovalProfile() {
        approvalProfilesHelperDefault.saveApprovalProfile();
    }


    @Test
    public void stepJ_AddCertificateProfile() {
        // Add Certificate Profile
        certificateProfileHelper.openPage(getAdminWebUrl());
        certificateProfileHelper.addCertificateProfile(TestData.CERTIFICATE_PROFILE_NAME);
    }

    @Test
    public void stepK_EditCertificateProfile() {
        // Edit certificate Profile
        certificateProfileHelper.openPage(getAdminWebUrl());
        certificateProfileHelper.openEditCertificateProfilePage(TestData.CERTIFICATE_PROFILE_NAME);

        // Set Approval Settings
        certificateProfileHelper.selectApprovalSetting(CertificateProfileHelper.ApprovalSetting.ADD_OR_EDIT_END_ENTITY, TestData.APPROVAL_PROFILE_NAME);
        certificateProfileHelper.selectApprovalSetting(CertificateProfileHelper.ApprovalSetting.KEY_RECOVERY, TestData.APPROVAL_PROFILE_NAME);
        certificateProfileHelper.selectApprovalSetting(CertificateProfileHelper.ApprovalSetting.REVOCATION, TestData.APPROVAL_PROFILE_NAME);

        // Set validity
        certificateProfileHelper.fillValidity("720d");
    }

    @Test
    public void stepL_SaveCertificateProfile() {
        // Save
        certificateProfileHelper.saveCertificateProfile();
    }

    @Test
    public void stepM_AddEndEntityProfile() {
        eeProfileHelper.openPage(this.getAdminWebUrl());
        eeProfileHelper.addEndEntityProfile(TestData.ENTITY_NAME);
    }

    @Test
    public void stepN_EditEndEntityProfile() {
        eeProfileHelper.openEditEndEntityProfilePage(TestData.ENTITY_NAME);
        eeProfileHelper.selectDefaultCa(this.getCaName());
        //Add DNS Name
        eeProfileHelper.setSubjectAlternativeName("DNS Name");
    }

    @Test
    public void stepO_SaveEndEntityProfile() {
        eeProfileHelper.saveEndEntityProfile(true);
    }

    @Test
    public void stepP_MakeNewCertificate() {
        raWebHelper.openPage(this.getRaWebUrl());
        raWebHelper.makeNewCertificateRequest();
    }

    @Test
    public void stepQ_SelectRequestTemplate() {
        raWebHelper.selectCertificateTypeByEndEntityName(TestData.ENTITY_NAME);
        raWebHelper.selectCertificationAuthorityByName(TestData.CA_NAME);
        raWebHelper.selectKeyPairGenerationProvided();
    }

    @Test
    public void stepR_insertCsrCertificate() {
        raWebHelper.fillClearCsrText(StringUtils.join(TestData.CERTIFICATE_REQUEST_PEM, "\n"));
    }

    @Test
    public void stepS_UploadCSRCertificate() {
        raWebHelper.clickUploadCsrButton();
    }

    @Test
    public void stepT_ProvideRequestInfo() {
        raWebHelper.fillMakeRequestEditCommonName("cn" + Calendar.getInstance().toString());
        raWebHelper.fillDnsName(TestData.VALIDATOR_BLACKLIST_SITE);
    }

    @Test
    public void stepU_downloadPem() {
        raWebHelper.clickDownloadPem();
        raWebHelper.assertApproveMessageDoesNotExist();
    }

}
