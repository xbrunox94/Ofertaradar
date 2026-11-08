package br.com.ofertaradar;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.*;

public class MainActivity extends Activity {
    int bg = Color.rgb(10, 15, 30), panel = Color.rgb(20, 29, 49);
    int cyan = Color.rgb(0, 220, 255), white = Color.WHITE;
    EditText searchBox;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(bg);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(20), dp(24), dp(20), dp(28));
        scroll.addView(page);

        TextView logo = text("◈ OfertaRadar", 28, cyan, true);
        page.addView(logo);
        page.addView(text("Encontre oportunidades. Compare antes de comprar.", 14, Color.LTGRAY, false));
        page.addView(space(22));

        TextView title = text("Radar de ofertas", 23, white, true);
        page.addView(title);
        page.addView(text("Pesquise um produto nas principais lojas do Brasil.", 14, Color.LTGRAY, false));
        page.addView(space(14));

        searchBox = new EditText(this);
        searchBox.setSingleLine(true);
        searchBox.setHint("Ex.: fone bluetooth, celular...");
        searchBox.setHintTextColor(Color.GRAY);
        searchBox.setTextColor(white);
        searchBox.setPadding(dp(14), dp(12), dp(14), dp(12));
        searchBox.setBackgroundColor(panel);
        page.addView(searchBox, new LinearLayout.LayoutParams(-1, dp(52)));

        Button search = button("Pesquisar ofertas");
        page.addView(search);
        search.setOnClickListener(v -> searchAll());

        page.addView(space(24));
        page.addView(text("Lojas populares", 20, white, true));
        page.addView(space(10));

        addStore(page, "Mercado Livre", "https://www.mercadolivre.com.br/");
        addStore(page, "Shopee", "https://shopee.com.br/");
        addStore(page, "Amazon Brasil", "https://www.amazon.com.br/");
        addStore(page, "AliExpress", "https://pt.aliexpress.com/");
        addStore(page, "Magazine Luiza", "https://www.magazineluiza.com.br/");

        page.addView(space(18));
        TextView note = text("Versão inicial • As pesquisas abrem as lojas no navegador. Preços e estoque são definidos pelas lojas. A integração automática e alertas serão adicionados em versões futuras.", 12, Color.LTGRAY, false);
        note.setPadding(dp(12), dp(12), dp(12), dp(12));
        note.setBackgroundColor(panel);
        page.addView(note);
        setContentView(scroll);
    }

    void searchAll() {
        String q = searchBox.getText().toString().trim();
        if (q.isEmpty()) { searchBox.setError("Digite um produto para pesquisar"); return; }
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
            .hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
        String url = "https://www.google.com/search?q=" + Uri.encode(q + " preço Brasil ofertas");
        open(url);
    }

    void addStore(LinearLayout page, String name, String url) {
        Button b = button("↗  " + name);
        page.addView(b);
        b.setOnClickListener(v -> {
            String q = searchBox.getText().toString().trim();
            if (!q.isEmpty()) {
                if (name.equals("Mercado Livre")) url = "https://lista.mercadolivre.com.br/" + Uri.encode(q);
                else if (name.equals("Shopee")) url = "https://shopee.com.br/search?keyword=" + Uri.encode(q);
                else if (name.equals("Amazon Brasil")) url = "https://www.amazon.com.br/s?k=" + Uri.encode(q);
                else if (name.equals("AliExpress")) url = "https://pt.aliexpress.com/w/wholesale-" + Uri.encode(q) + ".html";
                else if (name.equals("Magazine Luiza")) url = "https://www.magazineluiza.com.br/busca/" + Uri.encode(q) + "/";
            }
            open(url);
        });
    }

    void open(String url) {
        try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
        catch (Exception e) { Toast.makeText(this, "Não foi possível abrir o navegador.", Toast.LENGTH_SHORT).show(); }
    }

    Button button(String label) {
        Button b = new Button(this);
        b.setText(label); b.setTextColor(white); b.setAllCaps(false);
        b.setTextSize(15); b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(panel));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(52));
        lp.topMargin = dp(8); b.setLayoutParams(lp); return b;
    }
    TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }
    View space(int h) { View v = new View(this); v.setLayoutParams(new LinearLayout.LayoutParams(1, dp(h))); return v; }
    int dp(int x) { return (int)(x * getResources().getDisplayMetrics().density + 0.5f); }
}
