package io.ecocode.java.checks.social.privacy;

import io.ecocode.java.checks.helpers.TreeHelper;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Check the presence of the import com.google.android.ads.consent
 * <p>
 * To support publishers in meeting their duties under the EU User Consent Policy, Google offers a Consent SDK.
 * Hence, importing classes from com.google.android.ads.consent is considered as a good practice.
 */
@Rule(key = "SGDP001", name = "Google Consent")
public class GoogleAndroidAdsConsentRule extends BaseTreeVisitor implements JavaFileScanner {

    private static final String ERROR_MESSAGE_GOOGLE_ANDROID_ADS_CONSENT_NOT_USED = "Check user consent by using com.google.android.ads.consent.* ";

    @Override
    public void scanFile(JavaFileScannerContext context) {
        CompilationUnitTree cut = context.getTree();

        GoogleAndroidAdsConsentImports googleTrackerImport = new GoogleAndroidAdsConsentImports();

        for (ImportClauseTree importClauseTree : cut.imports()) {
            ImportTree importTree = null;

            if (importClauseTree.is(Tree.Kind.IMPORT)) {
                importTree = (ImportTree) importClauseTree;
            }

            if (importTree == null) {
                // discard empty statements, which can be part of imports
                continue;
            }

            googleTrackerImport.collectTrackerImport(importTree);
        }

        handleResult(context, googleTrackerImport);
        scan(cut);
    }

    private void handleResult(JavaFileScannerContext context, GoogleAndroidAdsConsentImports googleTrackerImport) {
        if (!googleTrackerImport.hasGoogleTrackerImports()) {
            context.addIssueOnProject(this, ERROR_MESSAGE_GOOGLE_ANDROID_ADS_CONSENT_NOT_USED);
        }
    }

    /**
     * New package com.google.android.gms.ads ??
     */
    private static class GoogleAndroidAdsConsentImports {
        private static final String STR_GOOGLE_ADS_CONSENT_IMPORT = "com.google.android.ads.consent";

        private final List<ImportTree> googleAdsConsentTrackerImport = new ArrayList<>();

        public List<ImportTree> getGoogleAdsConsentTrackerImports() {
            return googleAdsConsentTrackerImport;
        }

        public boolean hasGoogleTrackerImports() {
            return !googleAdsConsentTrackerImport.isEmpty();
        }

        public void collectTrackerImport(ImportTree importTree) {
            String importName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
            if (importName.startsWith(STR_GOOGLE_ADS_CONSENT_IMPORT)) {
                googleAdsConsentTrackerImport.add(importTree);
            }
        }
    }
}
