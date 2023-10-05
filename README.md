**EVENTR**

EVENTR is a webapplication for managing users, locations and events.

MAVEN is required!

start-command:
mvn spring-boot:run

Folgende User stehen zum testen zur verfügung: 'alexandra', 'plank', 'andi', 'liza'.

Das passwort für alle lautet '123456'.

Ihr account 'alexandra' beinhaltet Ihre mail-adresse (uibk), und somit sollten Sie eine mail erhalten wenn der Account eingeladen wird, das Voting endet oder gecanceled wird.

Lokale sind nicht löschbar, lediglich deaktivierbar.

Der Account 'alexandra' ist bereits teil einiger Events, sowohl vergangen als auch künftig. Zudem nimmt er bei 2 votings Teil, wovon eines nach kurzer Zeit durch den Scheduler beendet wird.

Nur der Ersteller kann zu einem Voting einladen, die Abstimmung frühzeitig beenden, oder canceln.

Jeder Teilnehmer kann ein Event verlassen.

Jeder Teilnehmer kann eine Abstimmnug verlassen mit Ausnahme dessen Ersteller (da er die Verwalterrolle trägt).

Der früheste Zeitpunkt um ein Event zu erstellen liegt bei 36 Stunden ab jetzt.

Ein Voting dauert 24 Stunden.
