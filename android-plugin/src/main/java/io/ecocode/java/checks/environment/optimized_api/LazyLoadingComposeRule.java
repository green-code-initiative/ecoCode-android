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

@Rule(key = "EC533")
@DeprecatedRuleKey(repositoryKey = "ecoCode-android", ruleKey = "EOPT003")
public class LazyLoadingComposeRule extends IssuableSubscriptionVisitor {

    private static final String WRONG_SCROLLABLE_IMPORT = "androidx.compose.foundation.";
    private static final String LAZY_LOADING_IMPORT = "androidx.compose.foundation.lazy";
    
    private static final Set<String> SCROLLABLE_COMPONENTS = Set.of(
        "ScrollableColumn",
        "VerticalScroller",
        "HorizontalScroller",
        "ScrollableRow"
    );
    
    private boolean hasSeenWrongImport = false;
    private boolean hasSeenLazyImport = false;
    private List<ImportTree> wrongImports = new ArrayList<>();
    private List<IdentifierTree> wrongUsages = new ArrayList<>();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.IMPORT, Tree.Kind.IDENTIFIER);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.IMPORT)) {
            checkImport((ImportTree) tree);
        } else {
            checkIdentifier((IdentifierTree) tree);
        }
    }

    private void checkImport(ImportTree importTree) {
        String fullQualifiedName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
        
        if (fullQualifiedName.startsWith(WRONG_SCROLLABLE_IMPORT) && 
            !fullQualifiedName.startsWith(LAZY_LOADING_IMPORT)) {
            hasSeenWrongImport = true;
            wrongImports.add(importTree);
        }
        
        if (fullQualifiedName.startsWith(LAZY_LOADING_IMPORT)) {
            hasSeenLazyImport = true;
        }
    }

    private void checkIdentifier(IdentifierTree identifierTree) {
        if (SCROLLABLE_COMPONENTS.contains(identifierTree.name())) {
            wrongUsages.add(identifierTree);
        }
    }

    @Override
    public void leaveFile(JavaFileScannerContext context) {
        // Remove super.leaveFile call since it's not needed
        if (hasSeenWrongImport && !hasSeenLazyImport) {
            // Report issues for wrong imports
            wrongImports.forEach(importTree -> 
                reportIssue(importTree, 
                    "Use Lazy components (LazyColumn, LazyRow, etc.) from androidx.compose.foundation.lazy " +
                    "instead of scrollable components for better performance with large datasets."));
            
            // Report issues for wrong component usages
            wrongUsages.forEach(identifierTree ->
                reportIssue(identifierTree,
                    "Replace " + identifierTree.name() + " with equivalent Lazy component " +
                    "(LazyColumn/LazyRow) for efficient lazy loading."));
        }
        
        // Reset state
        hasSeenWrongImport = false;
        hasSeenLazyImport = false;
        wrongImports.clear();
        wrongUsages.clear();
    }
}