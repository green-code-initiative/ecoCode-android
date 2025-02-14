/*
 * creedengo Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@green-code-initiative.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package android.service.voice;

public class VoiceInteractionSession {

    public void test() {
        VoiceInteractionSession session = new VoiceInteractionSession();// Noncompliant {{VoiceInteractionSession.setKeepAwake(false) should be called to limit battery drain.}}
    }

    public VoiceInteractionSession() {
    }

    public void setKeepAwake(boolean type) {
    }
}
