package br.com.launchersa;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final int bg = Color.rgb(12, 16, 14);
    private final int panel = Color.rgb(25, 32, 28);
    private final int green = Color.rgb(113, 220, 94);
    private final int muted = Color.rgb(177, 190, 180);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);
        getWindow().getDecorView().setSystemUiVisibility(0);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(bg);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(24), dp(28), dp(24), dp(24));
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        scroll.addView(root);

        TextView tag = text("SEU SERVIDOR • SA-MP", 12, green, true);
        tag.setLetterSpacing(0.12f);
        tag.setGravity(Gravity.CENTER);
        root.addView(tag, matchWrap());

        TextView title = text("LAUNCHER\nSAN ANDREAS", 30, Color.WHITE, true);
        title.setGravity(Gravity.CENTER);
        title.setLineSpacing(dp(2), 1.0f);
        LinearLayout.LayoutParams titleLp = matchWrap();
        titleLp.topMargin = dp(18);
        root.addView(title, titleLp);

        TextView subtitle = text("Sua cidade. Suas regras. Sua história.", 14, muted, false);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams subLp = matchWrap();
        subLp.topMargin = dp(8);
        root.addView(subtitle, subLp);

        View spacer = new View(this);
        root.addView(spacer, new LinearLayout.LayoutParams(1, dp(28)));

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(18), dp(18), dp(18), dp(18));
        card.setBackground(round(panel, dp(18)));
        root.addView(card, matchWrap());

        TextView downloadTitle = text("ARQUIVOS DO JOGO", 18, Color.WHITE, true);
        card.addView(downloadTitle, matchWrap());

        TextView status = text("Download ainda não configurado", 14, muted, false);
        LinearLayout.LayoutParams statusLp = matchWrap();
        statusLp.topMargin = dp(10);
        card.addView(status, statusLp);

        ProgressBar progress = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progress.setMax(100);
        progress.setProgress(0);
        progress.setProgressTintList(android.content.res.ColorStateList.valueOf(green));
        progress.setProgressBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(53, 65, 56)));
        LinearLayout.LayoutParams progressLp = matchWrap();
        progressLp.topMargin = dp(16);
        card.addView(progress, progressLp);

        TextView note = text("0% • Aguardando uma fonte autorizada dos arquivos originais.", 12, muted, false);
        LinearLayout.LayoutParams noteLp = matchWrap();
        noteLp.topMargin = dp(8);
        card.addView(note, noteLp);

        Button download = button("CONFIGURAR DOWNLOAD");
        LinearLayout.LayoutParams downloadLp = matchWrap();
        downloadLp.topMargin = dp(18);
        card.addView(download, downloadLp);
        download.setOnClickListener(v -> {
            status.setText("Próxima etapa: configurar a fonte autorizada dos arquivos.");
            note.setText("Nenhum arquivo foi baixado. O launcher ainda é uma base inicial.");
        });

        Button play = button("JOGAR");
        play.setEnabled(false);
        play.setAlpha(0.45f);
        LinearLayout.LayoutParams playLp = matchWrap();
        playLp.topMargin = dp(18);
        root.addView(play, playLp);

        TextView playNote = text("O botão será ativado após integrar e validar os arquivos do jogo e o cliente SA-MP.", 12, muted, false);
        playNote.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams playNoteLp = matchWrap();
        playNoteLp.topMargin = dp(10);
        root.addView(playNote, playNoteLp);

        TextView footer = text("VERSÃO 0.1.0 • BASE INICIAL", 11, muted, true);
        footer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams footerLp = matchWrap();
        footerLp.topMargin = dp(30);
        root.addView(footer, footerLp);

        setContentView(scroll);
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        return t;
    }

    private Button button(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(Color.rgb(10, 18, 11));
        b.setTextSize(14);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setAllCaps(false);
        b.setBackground(round(green, dp(12)));
        b.setPadding(dp(12), dp(10), dp(12), dp(10));
        return b;
    }

    private GradientDrawable round(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
