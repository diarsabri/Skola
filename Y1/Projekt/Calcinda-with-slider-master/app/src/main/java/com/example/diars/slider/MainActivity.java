package com.example.diars.slider;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.*;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public void onResume() {
        super.onResume();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Display display = getWindowManager().getDefaultDisplay();

        if (display.getRotation() == Surface.ROTATION_90 || display.getRotation() == Surface.ROTATION_270) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.START);
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,Gravity.START);

        }

    }

    /**
     *
     * @param curInput
     * @param s
     */
    public void cursorIncrement(EditText curInput, String s) {
        int cursoridx = curInput.getSelectionStart();
        curInput.setText(curInput.getEditableText().replace(curInput.getSelectionStart(), curInput.getSelectionEnd(), s));
        curInput.setSelection(cursoridx + s.length());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);





        //input field
        final EditText text = (EditText) findViewById(R.id.text);
        text.setShowSoftInputOnFocus(false);
        text.setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        //Memory
        final ArrayList<String> memlist = new ArrayList<>();

        //Integers
        final Button zero = (Button) findViewById(R.id.zero);
        final Button one = (Button) findViewById(R.id.one);
        final Button two = (Button) findViewById(R.id.two);
        final Button three = (Button) findViewById(R.id.three);
        final Button four = (Button) findViewById(R.id.four);
        final Button five = (Button) findViewById(R.id.five);
        final Button six = (Button) findViewById(R.id.six);
        final Button seven = (Button) findViewById(R.id.seven);
        final Button eight = (Button) findViewById(R.id.eight);
        final Button nine = (Button) findViewById(R.id.nine);

        //Simple arithmetic operators
        final Button plus = (Button) findViewById(R.id.plus);
        final Button minus = (Button) findViewById(R.id.minus);
        final Button divide = (Button) findViewById(R.id.div);
        final Button mult = (Button) findViewById(R.id.mult);
        final Button dot = (Button) findViewById(R.id.dot);

        //Special operators
        final Button equal = (Button) findViewById(R.id.equal);
        final Button delete = (Button) findViewById(R.id.del);

        //Memory
        final Button clear = (Button) findViewById(R.id.clear);
        final Button memplus = (Button) findViewById(R.id.memplus);
        final Button mem = (Button) findViewById(R.id.mem);

        //landscape operators and functions
        final Button lBracket = (Button) findViewById(R.id.lbracket);
        final Button rBracket = (Button) findViewById(R.id.rbracket);
        final Button power = (Button) findViewById(R.id.pwr);
        final Button comma = (Button) findViewById((R.id.comma));

        final Button sqrt = (Button) findViewById(R.id.sqrt);
        final Button factorial = (Button) findViewById(R.id.fac);

        final Button ln = (Button) findViewById(R.id.ln);
        final Button log = (Button) findViewById(R.id.log);

        //Trigonometric operators
        final Button sin = (Button) findViewById(R.id.sin);
        final Button cos = (Button) findViewById(R.id.cos);
        final Button tan = (Button) findViewById(R.id.tan);
        final Button arcsin = (Button) findViewById(R.id.arcsin);
        final Button arccos = (Button) findViewById(R.id.arccos);
        final Button arctan = (Button) findViewById(R.id.arctan);

        //Mathematical constants
        final Button pi =(Button) findViewById(R.id.pi);
        final Button e = (Button) findViewById(R.id.e);

        //variables & calculus
        final Button x = (Button) findViewById(R.id.xbtn);
        final Button differentiate = (Button) findViewById(R.id.deriv);
        final Button integrate = (Button) findViewById(R.id.integral);
        final Button solver = (Button) findViewById(R.id.solver);






        final Button x2 = (Button) findViewById(R.id.x2);
        final Button sol2 = (Button) findViewById(R.id.sol2);
        final Button int2 = (Button) findViewById(R.id.int2);
        final Button der2 = (Button) findViewById(R.id.der2);

        final Button sini2 = (Button) findViewById(R.id.sini2);
        final Button cosi2 = (Button) findViewById(R.id.cosi2);
        final Button tani2 = (Button) findViewById(R.id.tani2);
        final Button pwr2 = (Button) findViewById(R.id.pwr2);

        final Button sin2 = (Button) findViewById(R.id.sin2);
        final Button cos2 = (Button) findViewById(R.id.cos2);
        final Button tan2 = (Button) findViewById(R.id.tan2);
        final Button sqrt2 = (Button) findViewById(R.id.sqrt2);

        final Button ln2 = (Button) findViewById(R.id.ln2);
        final Button log2 = (Button) findViewById(R.id.log2);
        final Button fac2 = (Button) findViewById(R.id.fac2);
        final Button pi2 = (Button) findViewById(R.id.pi2);

        final Button lbracket2 = (Button) findViewById(R.id.lbracket2);
        final Button rbracket2 = (Button) findViewById(R.id.rbracket2);
        final Button e2 = (Button) findViewById(R.id.e2);
        final Button comma2 = (Button) findViewById(R.id.comma2);

        /*
            Drawer buttons
         */

        x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursorIncrement(text, "x");
            }
        });

        sol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "solve(f(x), x");
            }
        });

        int2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "int(f(x), x, a, b");
            }
        });

        der2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "der(f(x), x, p)");
            }
        });

        sini2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "arcsin(");
            }
        });

        cosi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "arccos(");
            }
        });

        tani2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "arctan(");
            }
        });

        sin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "sin(");
            }
        });

        cos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "cos(");
            }
        });

        tan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "tan(");
            }
        });

        pwr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "^");
            }
        });

        ln2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "ln(");
            }
        });

        log2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "log(");
            }
        });

        fac2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "!");
            }
        });

        pi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "pi");
            }
        });

        lbracket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "(");
            }
        });

        rbracket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, ")");
            }
        });

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "e");
            }
        });

        comma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, ",");
            }
        });













        /*
         Buttons 0-9
         */

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "9");
            }
        });

        /*
        Basic arithmetic operations for the calculator
        ( +-/* )
        */

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "-");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "/");
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "*");
            }
        });

        /*
        Actual calculator button ( equal ).
        Delete button for deletion of characters
        dot button, precisely as a dot button in a calculator should work.
        Equal,dot,delete buttons
         */
        equal.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                String string = text.getText().toString();
                if (string.equals("")) {
                    text.setText("");
                } else {
                    Expression e = new Expression(string);

                    text.setText(String.valueOf(e.calculate()));
                    text.setSelection(text.getText().length());
                }
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, ".");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursPos = text.getSelectionStart();

                if (text.length() > 0 && Math.abs(text.getSelectionStart() - text.getSelectionEnd()) > 0) {
                    text.setText(text.getEditableText().delete(text.getSelectionStart(), text.getSelectionEnd()));
                    text.setSelection(cursPos);

                } else if (text.length() > 0 && cursPos > 0) {
                    cursPos = text.getSelectionStart();
                    text.setText(text.getEditableText().delete(text.getSelectionStart() - 1, text.getSelectionEnd()));
                    text.setSelection(cursPos - 1);

                }
            }
        });

        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (text.length() > 0) {
                    int cursPos = text.getSelectionEnd();
                    text.setText(text.getEditableText().delete(0, cursPos));
                    return true;
                }
                return false;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(text.getEditableText().delete(0, text.length()));
            }
        });

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if ((text.length() > 0) || memlist.size() > 0) {
                    text.setText(text.getEditableText().delete(0, text.length()));
                    memlist.clear();
                    return true;
                }
                return false;
            }
        });

        mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memlist.add(text.getText().toString());
            }
        });

        memplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (memlist.size() > 0) {
                    String lastmemory = memlist.get(memlist.size() - 1);
                    cursorIncrement(text, lastmemory);
                } else {
                    return;
                }
            }
        });


        lBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "(");
            }
        });

        /**
         * Mathematical operators and functions that are only visible in landscape mode,
         * these include sinus, cosine, tangent, sqrt, arcsin, arccos, arctan, ln, log,
         *  factorial, powers, brackets as well as constants such as pi and e.
         */

        //Brackets
        lBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "(");
            }
        });

        rBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, ")");
            }
        });

        //Raise to the power of
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "^");
            }
        });

        comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, ",");
            }
        });

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "sqrt(");
            }
        });

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "!");
            }
        });

        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "ln(");
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "log(");
            }
        });


        //Trigonometric functions
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "sin(");
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "cos(");
            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "tan(");
            }
        });

        arcsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "arcsin(");
            }
        });

        arccos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "arccos(");
            }
        });

        arctan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "arctan(");
            }
        });

        //Constants
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "pi");
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "e");
            }
        });

        //Variables & calculus
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "x");
            }
        });

        differentiate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "der(f(x), x, p)");
            }
        });

        integrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "int(f(x), x, a, b");
            }
        });

        solver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorIncrement(text, "solve(f(x), x");
            }
        });

        /*
         * sets color of both background and text to invert on press
         * Creates an arraylist of all the buttons
         * @Param currentbutton -> assigns a final button that applies the changes to all the buttons
         */
        Button[] buttonlist = {one,two,three,four,five,six,seven,eight,nine,zero,plus,minus,mult,divide,delete,dot,equal,clear,mem,memplus, lBracket,
                rBracket, comma, power, sqrt, factorial, ln, log, sin, cos, tan, arcsin, arccos, arctan, pi, e, x, differentiate, integrate,
                solver};
        final ArrayList<Button> arlist = new ArrayList<>();
        arlist.addAll(Arrays.asList(buttonlist));
        for (int i = 0; i<arlist.size();i++) {
            final Button currentbutton = arlist.get(i);
            currentbutton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        currentbutton.setBackgroundColor(Color.rgb(32,194,14));
                        currentbutton.setTextColor(Color.BLACK);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        currentbutton.setBackgroundColor(Color.BLACK);
                        currentbutton.setTextColor(Color.rgb(32,194,14));
                    }
                    return false;
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
