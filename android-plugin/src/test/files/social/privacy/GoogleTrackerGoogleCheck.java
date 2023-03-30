import com.google.android.gms.analytics.Tracker; // Noncompliant {{Using com.google.android.gms.analytics.* is a potential threat for privacy.}}
import com.google.android.gms.analytics.*; // Noncompliant {{Using com.google.android.gms.analytics.* is a potential threat for privacy.}}
import com.google.android.gms.Test;
import com.google.android.gms.analytics.GoogleAnalytics; // Noncompliant {{Using com.google.android.gms.analytics.* is a potential threat for privacy.}}
import com.io.ecocode.MaClasse;

public class Test {
}
