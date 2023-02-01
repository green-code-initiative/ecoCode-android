import com.google.android.gms.analytics.Tracker; // Noncompliant {{Using com.google.android.gms.analytics.* is a potential threat for privacy.}}
import com.google.firebase.analytics.FirebaseAnalytics; // Noncompliant {{Using com.google.firebase.analytics.* is a potential threat for privacy.}}

public class Test {
}
