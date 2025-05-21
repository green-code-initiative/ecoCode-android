package io.ecocode.java.checks.environment.optimized_api;

import io.ecocode.java.checks.helpers.TreeHelper;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.sonar.plugins.java.api.tree.*;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;

@Rule(key = "EC533")
@DeprecatedRuleKey(repositoryKey = "ecoCode-android", ruleKey = "EOPT003")
public class LazyLoadingComposeRule extends IssuableSubscriptionVisitor {

    private static final String LAZY_LOADING_IMPORT = "androidx.compose.foundation.lazy";
    
    private static final Set<String> LEGACY_IMPORTS = Set.of(
        "android.view.View",
        "android.view.ViewGroup",
        "android.widget.TextView",
        "android.widget.ListView",
        "android.widget.GridView",
        "androidx.recyclerview.widget.RecyclerView"
    );
    
    private boolean hasSeenWrongImport = false;
    private boolean hasSeenLazyImport = false;
    private List<ImportTree> wrongImports = new ArrayList<>();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.IMPORT);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.IMPORT)) {
            checkImport((ImportTree) tree);
        }
    }

    private void checkImport(ImportTree importTree) {
        String fullQualifiedName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
        
        if (LEGACY_IMPORTS.contains(fullQualifiedName)) {
            hasSeenWrongImport = true;
            wrongImports.add(importTree);
        }
        
        if (fullQualifiedName.startsWith(LAZY_LOADING_IMPORT)) {
            hasSeenLazyImport = true;
        }
    }

    @Override
    public void leaveFile(JavaFileScannerContext context) {
        if (hasSeenWrongImport && !hasSeenLazyImport) {
            wrongImports.forEach(importTree -> 
                reportIssue(importTree, 
                    "Prefer using lazy loading view components from Jetpack-Compose to save energy"));
        }
        
        // Reset state
        hasSeenWrongImport = false;
        hasSeenLazyImport = false;
        wrongImports.clear();
    }
}
