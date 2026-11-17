package org.example;

public class FabriqueHtml implements FabriqueExport {
    @Override public String enTete() { return "<table>\n"; }

    @Override public String ligne(Document d) {
        return "  <tr><td>" + echapper(d.getTitre()) + "</td><td>"
                + d.getClass().getSimpleName() + "</td></tr>\n";
    }

    @Override public String pied() { return "</table>\n"; }

    // "Science & Vie" doit devenir "Science &amp; Vie" en HTML
    private String echapper(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}