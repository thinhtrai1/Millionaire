package com.example.millionaire;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    QuestionDatabase questionDatabase = new QuestionDatabase(this);
    QuestionDefault questionDefault = new QuestionDefault(this);
    static TextView tvQuestion, tvNumber, tvProgress, progressNumA, progressNumB, progressNumC, progressNumD;
    EditText loginCode;
    static Button btA, btB, btC, btD, loginBtn, specOkBtn, bt5050, btRelatives, btSpectator;
    String answer;
    boolean login_x;
    static ProgressBar progressBar, progressA, progressB, progressC, progressD;
    static CountDownTimer countDownTimer;
    int kq, aa, bb, cc, dd, soundPool1, soundPool2, soundPool3, soundPoolLose, soundPool5s;
    static int n, m, soundPoolWin, video_num;
    View questionView, spectatorView, loginLayout;;
    static SoundPool soundPool;
    static Switch soundSwitch;
    MediaPlayer mediaPlayer;
    VideoView videoView;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        questionView = findViewById(R.id.question_view);
        spectatorView = findViewById(R.id.spectator_layout);
        progressBar = findViewById(R.id.progess_bar);
        tvProgress = findViewById(R.id.progess_tv);
        loginLayout = findViewById(R.id.login_layout);
        loginCode = findViewById(R.id.login_code);
        loginBtn = findViewById(R.id.login_btn);
        tvNumber = findViewById(R.id.tv_number);
        tvQuestion = findViewById(R.id.question);
        btA = findViewById(R.id.a);
        btB = findViewById(R.id.b);
        btC = findViewById(R.id.c);
        btD = findViewById(R.id.d);
        bt5050 = findViewById(R.id.bt5050);
        btRelatives = findViewById(R.id.btRelatives);
        btSpectator = findViewById(R.id.btSpectator);
        specOkBtn = findViewById(R.id.spectator_OK_btn);
        progressA = findViewById(R.id.progess_a);
        progressB = findViewById(R.id.progess_b);
        progressC = findViewById(R.id.progess_c);
        progressD = findViewById(R.id.progess_d);
        progressNumA = findViewById(R.id.progress_num_a);
        progressNumB = findViewById(R.id.progress_num_b);
        progressNumC = findViewById(R.id.progress_num_c);
        progressNumD = findViewById(R.id.progress_num_d);
        soundSwitch = findViewById(R.id.soung_sw);
        mediaPlayer = MediaPlayer.create(this, R.raw.background);
        videoView = findViewById(R.id.video_view);

        // load data for the first run
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun) {
            questionDefault.addDefault();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attrs = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .setAudioAttributes(attrs)
                    .build();
        }
        soundPoolWin = soundPool.load(this, R.raw.win, 1);
        soundPool1 = soundPool.load(this, R.raw.help5050, 1);
        soundPool2 = soundPool.load(this, R.raw.spectator03, 1);
        soundPool3 = soundPool.load(this, R.raw.relatives, 1);
        soundPoolLose = soundPool.load(this, R.raw.sai, 1);
        soundPool5s = soundPool.load(this, R.raw.last_5s, 1);

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (soundSwitch.isChecked()) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    if (progressBar.getProgress() >= 250)
                        soundPool.play(soundPool5s, 1, 1, 0, 0, 1);
                } else {
                    if (mediaPlayer.isPlaying())
                        mediaPlayer.pause();
                    soundPool.autoPause();
                }
            }
        });
        countDownTimer = new CountDownTimer(30200, 100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress(progressBar.getProgress() + 1);
                if (progressBar.getProgress() == 250)
                    if (soundSwitch.isChecked()) {
                        soundPool.play(soundPool5s, 1, 1, 0, 0, 1);
                    }
            }

            @Override
            public void onFinish() {
                lose_notification();
            }
        };

        questionDatabase.displayQuesstion();

        btA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (login_x == false) {
                    questionView.setVisibility(View.GONE);
                    loginLayout.setVisibility(View.VISIBLE);
                    Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    loginLayout.setAnimation(fadeIn);
                    login_x = true;
                } else {
                    questionView.setVisibility(View.VISIBLE);
                    loginLayout.setVisibility(View.GONE);
                    Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                    loginLayout.setAnimation(fadeOut);
                    login_x = false;
                }
                return true;
            }
        });
        btB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                tvQuestion.setText("MILLIONAIRE\nVersion 1.1\nNguyễn Đức Thịnh\nVery handsome and cute :)");
                return false;
            }
        });
        btC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        btD.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("DELETE ALL QUESTION");
                builder.setIcon(R.drawable.warning);
                builder.setMessage("Are you sure to delete all questions?");
                builder.setCancelable(true);
                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        questionDatabase.deleteAllData();
                        questionDefault.addDefault();
                        Toast.makeText(MainActivity.this, "Delete All Question", Toast.LENGTH_LONG).show();
                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCode.getText().toString().equals("")) {
                    Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
                    startActivity(intent);
                } else {
                    tvQuestion.setVisibility(View.VISIBLE);
                    loginLayout.setVisibility(View.GONE);
                    Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                    loginLayout.setAnimation(fadeOut);
                }
            }
        });
        btA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "a";
                checkdb();
            }
        });
        btB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "b";
                checkdb();
            }
        });
        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "c";
                checkdb();
            }
        });
        btD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "d";
                checkdb();
            }
        });
        bt5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_num = video_num + 1;
                if (soundSwitch.isChecked())
                    soundPool.play(soundPool1, 1, 1, 0, 0, 1);
                bt5050.setVisibility(View.GONE);
                help_5050();
            }
        });
        btRelatives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_num = video_num + 1;
                if (soundSwitch.isChecked())
                    soundPool.play(soundPool3, 1, 1, 0, 0, 1);
                btRelatives.setVisibility(View.GONE);
                help_relatives();
            }
        });
        btSpectator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_num = video_num + 1;
                if (soundSwitch.isChecked())
                    soundPool.play(soundPool2, 1, 1, 1, 0, 1);
                btSpectator.setVisibility(View.GONE);
                help_spectator();
            }
        });
        specOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionView.setVisibility(View.VISIBLE);
                spectatorView.setVisibility(View.GONE);
            }
        });
    }

    public void onBackPressed() {
        finish();
    }

    @Override
    public void onStop() {
        soundPool.release();
        mediaPlayer.release();
        countDownTimer.cancel();
        finish();
        super.onStop();
    }

    void checkdb() {
        soundPool.autoPause();
        spectatorView.setVisibility(View.GONE);
        questionView.setVisibility(View.VISIBLE);
        loginLayout.setVisibility(View.GONE);
        if (answer.equals(questionDatabase.cursor2.getString(8))) {
            questionDatabase.displayQuesstion();
        } else {
            lose_notification();
        }
    }

    void lose_notification() {
        if (video_num == 3) {
            tvNumber.setVisibility(View.GONE);
            Uri uri = Uri.parse("android.resource://com.example.millionaire/" + R.raw.vo_tran);
            videoView.setVideoURI(uri);
            videoView.setVisibility(View.VISIBLE);
            videoView.start();
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    videoView.setVisibility(View.GONE);
                    tvNumber.setVisibility(View.VISIBLE);
                }
            });
        }
        soundPool.autoPause();
        if (soundSwitch.isChecked())
            soundPool.play(soundPoolLose, 1, 1, 0, 0, 1);
        bt5050.setVisibility(View.VISIBLE);
        btRelatives.setVisibility(View.VISIBLE);
        btSpectator.setVisibility(View.VISIBLE);
        spectatorView.setVisibility(View.GONE);
        loginLayout.setVisibility(View.GONE);
        questionView.setVisibility(View.VISIBLE);
        String tt;
        if (questionDatabase.cursor2.getString(8).equals("a"))
            tt = questionDatabase.cursor2.getString(4);
        else if (questionDatabase.cursor2.getString(8).equals("b"))
            tt = questionDatabase.cursor2.getString(5);
        else if (questionDatabase.cursor2.getString(8).equals("c"))
            tt = questionDatabase.cursor2.getString(6);
        else tt = questionDatabase.cursor2.getString(7);
        Toast.makeText(this, questionDatabase.cursor2.getString(3) + "\n" + tt.toUpperCase(), Toast.LENGTH_LONG).show();
        questionDatabase.x = 0;
        questionDatabase.displayQuesstion();
        tvProgress.setTextColor(Color.WHITE);
        tvNumber.setBackgroundColor(Color.BLACK);
    }

    void help_5050() {
        if (questionDatabase.cursor2.getString(8).equals("a"))
            kq = 1;
        else if (questionDatabase.cursor2.getString(8).equals("b"))
            kq = 2;
        else if (questionDatabase.cursor2.getString(8).equals("c"))
            kq = 3;
        else if (questionDatabase.cursor2.getString(8).equals("d"))
            kq = 4;
        rd_5050_1();
        rd_5050_2();
    }

    void rd_5050_1() {
        n = 1+random.nextInt(4);
        if (n == kq)
            rd_5050_1();
    }

    void rd_5050_2() {
        m = 1+random.nextInt(4);
        if (m == kq || m == n)
            rd_5050_2();
        else {
            if (n == 1 || m == 1) btA.setText("");
            if (n == 2 || m == 2) btB.setText("");
            if (n == 3 || m == 3) btC.setText("");
            if (n == 4 || m == 4) btD.setText("");
        }
    }

    void help_relatives() {
        int helpRela = 1+random.nextInt(15);
        if (helpRela == n || helpRela == m) help_relatives();
        else {
            String helpRelatives;
            if (helpRela == 1) helpRelatives = "A";
            else if (helpRela == 2) helpRelatives = "B";
            else if (helpRela == 3) helpRelatives = "C";
            else if (helpRela == 4) helpRelatives = "D";
            else helpRelatives = questionDatabase.cursor2.getString(8).toUpperCase();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.relatives);
            builder.setTitle("Bố mày bảo " + helpRelatives + " nhé!");
            builder.setCancelable(true);
            builder.create();
            builder.show();
        }
    }

    void help_spectator() {
        int a, b, c, d;
        a = b = c = d = aa = bb = cc = dd = 0;
        if (n != 1 && m != 1) a = random.nextInt(90);
        if (n != 2 && m != 2) b = random.nextInt(90);
        if (n != 3 && m != 3) c = random.nextInt(90);
        if (n != 4 && m != 4) d = random.nextInt(90);

        if (questionDatabase.cursor2.getString(8).equals("a")) {
            a = 100;
        } else if (questionDatabase.cursor2.getString(8).equals("b")) {
            b = 100;
        } else if (questionDatabase.cursor2.getString(8).equals("c")) {
            c = 100;
        } else if (questionDatabase.cursor2.getString(8).equals("d")) {
            d = 100;
        }

        aa = a * 100 / (a + b + c + d);
        bb = b * 100 / (a + b + c + d);
        cc = c * 100 / (a + b + c + d);
        if (n != 4 && m != 4) dd = 100 - (aa + bb + cc);
        else {
            if (a == 100) aa++;
            else if (b == 100) bb++;
            else if (c == 100) cc++;
            else if (d == 100) dd++;
        }
        progressA.setProgress(0);
        progressB.setProgress(0);
        progressC.setProgress(0);
        progressD.setProgress(0);
        progressA.setSecondaryProgress(0);
        progressB.setSecondaryProgress(0);
        progressC.setSecondaryProgress(0);
        progressD.setSecondaryProgress(0);

        CountDownTimer countDownTimer = new CountDownTimer(3000, 27) {
            @Override
            public void onTick(long l) {
                if (progressA.getProgress() < aa) {
                    progressA.setProgress(progressA.getProgress() + 1);
                    progressA.setSecondaryProgress(progressA.getProgress() + 3);
                }

                if (progressB.getProgress() < bb) {
                    progressB.setProgress(progressB.getProgress() + 1);
                    progressB.setSecondaryProgress(progressB.getProgress() + 3);
                }

                if (progressC.getProgress() < cc) {
                    progressC.setProgress(progressC.getProgress() + 1);
                    progressC.setSecondaryProgress(progressC.getProgress() + 3);
                }

                if (progressD.getProgress() < dd) {
                    progressD.setProgress(progressD.getProgress() + 1);
                    progressD.setSecondaryProgress(progressD.getProgress() + 3);
                }
                progressNumA.setText(progressA.getProgress() + "%");
                progressNumB.setText(progressB.getProgress() + "%");
                progressNumC.setText(progressC.getProgress() + "%");
                progressNumD.setText(progressD.getProgress() + "%");
            }

            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();

        questionView.setVisibility(View.GONE);
        spectatorView.setVisibility(View.VISIBLE);
    }
}

