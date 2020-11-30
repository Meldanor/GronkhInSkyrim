<template>
  <div class="about">
    <h3 class="title is-3">Ist das ein Amulett von Mara? (FAQ)</h3>
    <h4 class="subtitle is-4">Warum?</h4>
    <i>
      TL;DR: Weil ich mich mit
      <external-link link="https://de.wikipedia.org/wiki/Texterkennung" text="OCR"/>
        und
      <external-link link="https://de.wikipedia.org/wiki/Data-Mining" text="Data Mining"/>
      beschäftigen wollte und sein Skyrim Playthrough super gut finde!
    </i>
    <p>
      Als Gronkh seinen Skyrim Playthrough startete, war ich begeistert! Ich habe in dem Spiel
      auch einiges an Zeit versenkt, aber es nie durchgespielt oder "sonderlich weit gespielt".
      Im Zuge von Corona 2020 waren Let's Plays, Streams und Co ein sozialer Ersatz. Ich habe seine
      Playlist geschaut und mich Tag für Tag darüber gefreut.
    </p>
    <p>
      Ein Running Gag ist seine <del>zwanghafter Sammelsucht</del> chronische Überladung. Ob
      Dwemerüberreste, Knochenmehl oder Pilze - alles findet seinen Weg in Eriks Taschen.
      Völlig verständlich in einem epischen Spiel wie Skyrim! Er installierte eine Pferdemod, um
      seine <del>unnützen</del> gerade nicht benötigten Gegenstände anzuladen, schloss Freunschaften
      mit vielen belastbaren Damen und hat irgendwann sein maximales Tragegewicht via Mod erhöht.
    </p>
    <p>
      Mich hatte interessiert, wie häufig er überladen ist und wir als Zuschauer athmosphärische
      Kamerafahrten durch die Höhlen in Slow Motion erleben. <del>Wie viel Lebenszeit verliert er,
      wie viel verlieren wir?</del>.
    </p>
    <p>
      Ich hatte ein paar
      <external-link text="Videos über neuronale Netze" link="https://www.youtube.com/watch?v=aircAruvnKk"/>
      gesehen (was ich jedem an Künstlicher Intelligenz interessierten Menschen empfehle!). Ein
      klassischer Beispiel ist die Texterkennung von Buchstaben, wie sie seit Jahrzehnten bei
      der Post eingesetzt wird und mit dem Boom des Internets, Clouds und Machinellem
      Lernen ziemlich verbreitet ist.
    </p>
    <p>
      Bei einer Folge in Skyrim bemerkte ich, dass das Skyrim UI Weiß auf Schwarze Schrift
      verwendete und man doch OCR / Texterkennung ausprobieren könnte. Aber ich wollte ein Ziel
      bei dem Projekt haben! Das Projekt müsste eine OCR Engine hervorbringen UND die Videos
      analysieren. Beides ist mir für in der Menge recht unbekannt und versuche ich beides
      umzusetzen, werde ich demotiviert aufgeben.
    </p>
    <p>
      Ich entschied mich also nur die Daten zu analysieren und eine vorhandene Engine zu verwenden.
      Ebenfalls wollte ich mich mit der Visualisierungs Bibliothek
      <external-link title="Vega-Lite" link="https://vega.github.io/vega-lite/"/>
      beschäftigen. Da ich nun das Ziel des Projektes kannte und den Scope festlegte, wollte ich
      ein Ergebnis haben.
    </p>
    <p>
      Corona ist eine beschissene Zeit. Die Stimmung ist doof und positive Menschen wie Erik,
      <external-link link="https://www.twitch.tv/xpandorya" text="Pandorya"/>,
      <external-link link="https://www.youtube.com/Kapuzenwurm" text="Kapuzenwurm"/> oder
      <external-link link="https://www.twitch.tv/tobinatorlp" text="Tobinator"/> (und viele
      weitere!) geben ihr bestes, die Stimmung zu erheben. Da dachte ich mir - wenn ich eine kleine
      witzige Seite erstelle, kann ich meinen Teil auch dazu beitragen?
    </p>
    <h4 class="subtitle is-4">Wird es mehr geben?</h4>
    <p>
      Ich weiß es nicht. Das Projekt brauchte ein paar Wochen mit immer mal wieder ein bis vier
      Stunden Arbeit. Im aktuellen Log (siehe unten für Details) habe ich bisher nur Gewicht
      und Gold extrahiert - die Visualisierung beinhaltet nur das Gewicht. Vielleicht erstelle
      ich noch den Graphen für das Gold. Aber im Endeffekt habe ich meine persönlichen Ziele fertig
      und andere Projekte benötigen meine Zeit.
    </p>
    <p>
      Weitere Ideen waren noch:
    </p>
    <div class="content">
      <ul class="has-text-left">
        <li>Skills – bei Skillsteigerungen gibt es eine feste Einblendung in der Mitte</li>
        <li>
          Weg des Helden – bei <b>Zelda: Breath of the Wild</b> gibt es eine Historie, wo Link
          sich durch Hyrule über die Zeit bewegt. Ab und zu werden Ortsnamen bei Erik eingeblendet.
          Hätte ich eine Liste von Ortsnamen+Koordinaten auf der Skyrim Karte, könnte ich so etwas
          ähnliches machen.
        </li>
      </ul>
    </div>
    <h4 class="subtitle is-4">Wer?</h4>
    <p>
      Mein Name ist Kilian Gärtner und ich programmiere seit 10+ Jahren
      unterschiedlichen Kram. Mehr dazu findet man auf
      <external-link text="meiner Miniwebsite" link="https://www.meldanor.me"/>.
      Ich interessiere mich für jeden technischen Kram und Data-Mining, KI und Co ist noch ein
      großes Fragezeichen bei mir. Die Theorie dazu kann ich ein wenig, aber praktisch hatte ich
      fast nie Kontakt damit.
    </p>
    <h4 class="subtitle is-4">Wie?</h4>
    <i>
      TL;DR: Mit
      <external-link link="https://ffmpeg.org/" text="ffmpeg"/> einzelne Frames aus den Videos
      extrahiert, dann mit
      <external-link link="https://github.com/tesseract-ocr/" text="Tesseract-OCR"/> aus
      Ausschnitten des Frames den Text erkennen und mit einem Java Program die Daten bereinigen.
      Die Visualisierung erfolgt mit
      <external-link link="https://vega.github.io/vega-lite/" text="Vega-Lite"/>, die Website
      ist in <external-link link="https://v3.vuejs.org/guide/installation.html" text="Vue3"/>
      geschrieben.
    </i>
    <p>
      Ihr findet den Quellcode auf
      <external-link link="https://github.com/Meldanor/GronkhInSkyrim" text="GitHub"/>
    </p>
    <p>
      Zum Zeitpunkt dieses Textes habe ich 300 Episoden analyisiert. Da mir 1080p als Auflösung
      reicht, belegen diese "nur" 330 GB auf meinem NAS. Mit ffmpeg und Tessaract hatte ich
      die zwei Haupttools, nun mussten diese mit einem eigenen Tool zusammengeklebt und die Daten
      analyisiert werden. Java ist dafür mein Werkzeug der Wahl. Es ist sehr schnell, parallelisiert
      unheimlich gut und ich programmiere es seit mehr als zehn Jahren.
    </p>
    <p>
      Das Tool extrahiert die Meta Daten aus der Serie wie Episoden Namen und totale Laufzeit der
      Serie mit <code>ffprobe</code>. Die Namen habe ich noch recht manuel eingegeben, das Unicode
      Zeichen im Video Titel <del>hat mich viel Zeit und Nerven gekostet</del> ist einfach
      wunderbar. Dann schneidet es mit <code>ffmpeg</code> die einzelnen Frames aus. Bei 60
      Frames pro Sekunde macht es aber keinen Sinn, jeden einzelnen Frame zu analyisieren -
      viele Änderungen am Gewicht passieren nur alle paar Sekunden. Deswegen werden derzeit nur
      2 Frames pro Sekunde extrahiert. Das Extrahieren belastet die CPU mit allen Kernen und dauert
      pro Video ein paar Minuten.
    </p>
    <p>
      Die Frames werden in eine RAM Disk geschrieben, damit darauf einerseits schnell zugegriffen
      werden kann und andererseits sich die SSD nicht abnutzt. In einem ersten Versuch wurden alle
      Frames aus allen Videos extrahiert und zwischengespeichert, damit man diesen Schritt sich
      sparen kann. Jedoch wurden die extrahierten Bilder min. so groß wie die Videos und schon nach
      50 Videos hatte ich mehrere hundert Gigabyte benutzt - mehr als mein NAS mit 6TB halten
      konnte. Also für jeden Run in den RAM.
    </p>
    <p>
      Die Frames liegen nun als JPEG vor. Würde man den kompletten Frame in eine OCR Engine werfen,
      würde man sehr viele Informationen sehr ungeordnet erhalten. Deswegen wird zuerst ein Teil
      des Bildes ausgeschnitten - dort wo das Gold und das aktuelle Gewicht angezeigt wird.
      Dieser Frame wird in Tesseract reingepumpt (im wahrsten Sinne des Wortes!) und als Antwort
      erhält man einen Text. Das Java Programm checkt, ob der Text Gewicht oder Gold enthält
      und extrahiert entsprechend die Daten. Diese werden mit einem Zeitstempel versehen und
      in einen großen Log geschrieben.
    </p>
    <p>
      OCR hat eine enorm gute Erkennungsrate, ca. 99,7% - aber das Inventar ist leicht durchsichtig
      und je nach Hintergrund im Frame ist der Kontrast zu schlecht, um es korrekt zu extrahieren.
      Bei einer aktuellen Länge von 669.769 Sekunden (ca. 187h) sind das 1.339.538 Frames und
      mögliche Datenpunkte. Bei einer Fehlerquoute von 0,03% sind das durchschnittlich 40186,14
      Fehler. Es kann sein, dass eine <code>1</code> als <code>!</code> interpretiert oder
      der <code>/</code> nicht korrekt erkannt wird. Deswegen gibt es ein paar Fehler in den Daten,
      die man mit Statistik bereinigen könnte. Könnte. Da aber der Spieler selber eine Truhe
      komplett looten kann und Erik auch so mal das 10-20x auf einmal trägt, sind selbst
      statistische Ausreißer valide Datenpunkte. Für eine wirkliche Bereinigung der Daten fehlt
      mir noch das Wissen.
    </p>
    <p>
      Ein weiteres Problem ist die Datenmenge. Je größer die Daten, desto langsamer die
      Visualisierung und desto un-interaktiver. Viele Daten sind redundant. Öffnet Erik sein
      Inventar und sucht nach dem Amulett von Mara, verändert sich sein Gewicht nicht - das Program
      aber extrahiert 2 Datenpunkte pro Sekunde. Für 300 Episoden sind es ca 260.000 Datenpunkte –
      das entspricht ca. 36 Stunden, die Erik nur im Inventarscreen verbracht hat.
      <del>Wundert es wen?</del>. Die Daten werden derart komprimiert, dass sofern sich kein Wert
      ändert, nur der Anfang und das Ende der Zeitperiode gespeichert wird. So werden aus 260.000
      Datenpunkte ca. 13.000 – was einer Komprimierung von 1:20 entspricht und enorm gut ist!
    </p>
    <p>
      Das Program wurde in einen Docker Container gepackt und auf meinem Unraid Server ausgeführt.
      Der Server ist ein Ryzen 5 3600 mit 8 Kernen und somit 16 Threads und hat 32 GB an RAM.
      Die Videos liegen auf einer normalen HDD. Das Analyisieren erfolgte voll parallelisiert, so
      dass ich alle 16 Threads gleichzeitig nutzen konnte. Dennoch dauerte eine komplette Analyse
      ca. 26 Stunden! Aber bis ich dahin kam wurden viele Stunden mit Tests
      und kleinen Analysen ausgeführt. Die reine Rechenzeit liegt also bei locker 50h.
    </p>
    <p>
      Die Daten liegen nun als Log vor, sind komprimiert und es geht nun an die Visualisierung.
      <code>Vega-Lite</code> nutzt eine gewisse Gramatik, um komplexe Visualisierungen im Web zu
      schaffen. Alternativ könnte ich
      <external-link link="https://d3js.org/" text="d3"/> nutzen, aber d3 ist super fummelig.
      Ich wollte auch die neue Version von Vue3 ausprobieren und habe es mit dieser Website getan.
      Die Website ist sehr sehr simpel und weicht nur minimal von der Boilerplate ab - mir
      persönlich reicht dies aber.
    </p>
    <p>
      Die Website ist auf einer Hetzner Cloud Machine gehostet, liegt in einer nginx Docker und wird
      per reverse Proxy von Traeffik mit einem Zertifikat versehen. Die Domain kostet mich 10€ im
      Jahr und ist bei
      <external-link link="https://www.gandi.net/en" text="gandi"/> gehostet - womit ich seit Jahren
      sehr gute Erfahrungen gemacht habe.
    </p>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import ExternalLink from '../components/ExternalLink.vue';

export default defineComponent({
  name: 'About',
  components: {
    ExternalLink
  },
});
</script>

<style lang="scss">
.about {
  max-width: 960px;
  margin-right: auto;
  margin-left: auto;
}
h4 {
  text-align: left;
  text-decoration: underline;
  margin-top: 2.5rem;
  margin-bottom: 1rem;
}

i {
  margin-top: 1rem;
  margin-bottom: 1rem;
}

p {
  text-align: justify;
  margin-bottom: 0.5rem;
  margin-top: 1rem;
}
</style>
