package com.example.apptest;

import android.app.Activity;
import android.view.View;

public class KeyboardHandle extends Activity{
	public boolean KeyBtnOnClick(View v) {
		switch(v.getId()) {
		//영어 대문자
		case R.id.Q : keySend("Q"); break;	case R.id.W : keySend("W"); break;
		case R.id.E : keySend("E"); break;	case R.id.R : keySend("R"); break;
		case R.id.T : keySend("T"); break;	case R.id.Y : keySend("Y"); break;
		case R.id.U : keySend("U"); break;	case R.id.I : keySend("I"); break;
		case R.id.O : keySend("O"); break;	case R.id.P : keySend("P"); break;
		case R.id.A : keySend("A"); break;	case R.id.S : keySend("S"); break;
		case R.id.D : keySend("D"); break;	case R.id.F : keySend("F"); break;
		case R.id.G : keySend("G"); break;	case R.id.H : keySend("H"); break;
		case R.id.J : keySend("J"); break;	case R.id.K : keySend("K"); break;
		case R.id.L : keySend("L"); break;	case R.id.Z : keySend("Z"); break;
		case R.id.X : keySend("X"); break;	case R.id.C : keySend("C"); break;
		case R.id.V : keySend("V"); break;	case R.id.B : keySend("B"); break;
		case R.id.N : keySend("N"); break;	case R.id.M : keySend("M"); break;//
		
		//영어 소문자
		case R.id.q : keySend("q"); break;	case R.id.w : keySend("w"); break;
		case R.id.e : keySend("e"); break;	case R.id.r : keySend("r"); break;
		case R.id.t : keySend("t"); break;	case R.id.y : keySend("y"); break;
		case R.id.u : keySend("u"); break;	case R.id.i : keySend("i"); break;
		case R.id.o : keySend("o"); break;	case R.id.p : keySend("p"); break;
		case R.id.a : keySend("a"); break;	case R.id.s : keySend("s"); break;
		case R.id.d : keySend("d"); break;	case R.id.f : keySend("f"); break;
		case R.id.g : keySend("g"); break;	case R.id.h : keySend("h"); break;
		case R.id.j : keySend("j"); break;	case R.id.k : keySend("k"); break;
		case R.id.l : keySend("l"); break;	case R.id.z : keySend("z"); break;
		case R.id.x : keySend("x"); break;	case R.id.c : keySend("c"); break;
		case R.id.v : keySend("v"); break;	case R.id.b : keySend("b"); break;
		case R.id.n : keySend("n"); break;	case R.id.m : keySend("m"); break;//
		
		//한글
		case R.id.hq : keySend("q"); break;	case R.id.hw : keySend("w"); break;
		case R.id.he : keySend("e"); break;	case R.id.hr : keySend("r"); break;
		case R.id.ht : keySend("t"); break;	case R.id.hy : keySend("y"); break;
		case R.id.hu : keySend("u"); break;	case R.id.hi : keySend("i"); break;
		case R.id.ho : keySend("o"); break;	case R.id.hp : keySend("p"); break;
		case R.id.ha : keySend("a"); break;	case R.id.hs : keySend("s"); break;
		case R.id.hd : keySend("d"); break;	case R.id.hf : keySend("f"); break;
		case R.id.hg : keySend("g"); break;	case R.id.hh : keySend("h"); break;
		case R.id.hj : keySend("j"); break;	case R.id.hk : keySend("k"); break;
		case R.id.hl : keySend("l"); break;	case R.id.hz : keySend("z"); break;
		case R.id.hx : keySend("x"); break;	case R.id.hc : keySend("c"); break;
		case R.id.hv : keySend("v"); break;	case R.id.hb : keySend("b"); break;
		case R.id.hn : keySend("n"); break;	case R.id.hm : keySend("m"); break;//
		
		//한글 쌍자음
		case R.id.hbq : keySend("Q"); break;	case R.id.hbw : keySend("W"); break;
		case R.id.hbe : keySend("E"); break;	case R.id.hbr : keySend("R"); break;
		case R.id.hbt : keySend("T"); break;	case R.id.hby : keySend("y"); break;
		case R.id.hbu : keySend("u"); break;	case R.id.hbi : keySend("i"); break;
		case R.id.hbo : keySend("o"); break;	case R.id.hbp : keySend("p"); break;
		case R.id.hba : keySend("a"); break;	case R.id.hbs : keySend("s"); break;
		case R.id.hbd : keySend("d"); break;	case R.id.hbf : keySend("f"); break;
		case R.id.hbg : keySend("g"); break;	case R.id.hbh : keySend("h"); break;
		case R.id.hbj : keySend("j"); break;	case R.id.hbk : keySend("k"); break;
		case R.id.hbl : keySend("k"); break;	case R.id.hbz : keySend("z"); break;
		case R.id.hbx : keySend("x"); break;	case R.id.hbc : keySend("c"); break;
		case R.id.hbv : keySend("v"); break;	case R.id.hbb : keySend("b"); break;
		case R.id.hbn : keySend("n"); break;	case R.id.hbm : keySend("m"); break;//
		
		//숫자 + 기호
		case R.id.s_1 : keySend("1"); break;	case R.id.s_2 : keySend("2"); break;
		case R.id.s_3 : keySend("3"); break;	case R.id.s_4 : keySend("4"); break;
		case R.id.s_5 : keySend("5"); break;	case R.id.s_6 : keySend("6"); break;
		case R.id.s_7 : keySend("7"); break;	case R.id.s_8 : keySend("8"); break;
		case R.id.s_9 : keySend("9"); break;	case R.id.s_0 : keySend("0"); break;
		case R.id.s_a : keySend("-"); break;	case R.id.s_s : keySend("/"); break;
		case R.id.s_d : keySend(":"); break;	case R.id.s_f : keySend(";"); break;
		case R.id.s_g : keySend("("); break;	case R.id.s_h : keySend(")"); break;
		case R.id.s_j : keySend("s_j"); break;	case R.id.s_k : keySend("&"); break;
		case R.id.s_l : keySend("@"); break;	case R.id.s_z : keySend("s_z"); break;
		case R.id.s_c : keySend("."); break;	case R.id.s_v : keySend(","); break;
		case R.id.s_b : keySend("?"); break;	case R.id.s_n : keySend("!"); break;
		case R.id.s_m : keySend("'"); break;
		
		//기호
		case R.id.s2_q : keySend("["); break;	case R.id.s2_w : keySend("]"); break;
		case R.id.s2_e : keySend("{"); break;	case R.id.s2_r : keySend("}"); break;
		case R.id.s2_t : keySend("#"); break;	case R.id.s2_y : keySend("%"); break;
		case R.id.s2_u : keySend("^"); break;	case R.id.s2_i : keySend("*"); break;
		case R.id.s2_o : keySend("+"); break;	case R.id.s2_p : keySend("="); break;
		case R.id.s2_a : keySend("_"); break;	case R.id.s2_s : keySend("|"); break;
		case R.id.s2_d : keySend("~"); break;	case R.id.s2_f : keySend("<"); break;
		case R.id.s2_g : keySend(">"); break;	case R.id.s2_h : keySend("$"); break;
		case R.id.s2_j : keySend("s_j"); break;	case R.id.s2_k : keySend("&"); break;
		case R.id.s2_l : keySend("@"); break;	case R.id.s2_z : keySend("s_z"); break;
		case R.id.s2_c : keySend("."); break;	case R.id.s2_v : keySend(","); break;
		case R.id.s2_b : keySend("?"); break;	case R.id.s2_n : keySend("!"); break;
		case R.id.s2_m : keySend("'"); break;
		
		//ect
		case R.id.f1 : keySend("f1"); break;	case R.id.home : keySend("home"); break;
		case R.id.f2 : keySend("f2"); break;	case R.id.pgup : keySend("pgup"); break;
		case R.id.f3 : keySend("f3"); break;	case R.id.up : keySend("up"); break;
		case R.id.f4 : keySend("f4"); break;	case R.id.end_1 : keySend("end"); break;
		case R.id.f5 : keySend("f5"); break;	case R.id.pgdn : keySend("pgdn"); break;
		case R.id.f6 : keySend("f6"); break;	case R.id.h_ctrl : keySend("h_ctrl"); break;
		case R.id.f7 : keySend("f7"); break;	case R.id.left : keySend("left"); break;
		case R.id.f8 : keySend("f8"); break;	case R.id.down : keySend("down"); break;
		case R.id.f9 : keySend("f9"); break;	case R.id.right : keySend("right"); break;
		case R.id.f10 : keySend("f10"); break;	case R.id.f11 : keySend("f11"); break;	
		case R.id.f12 : keySend("f12"); break;	case R.id.prtsc : keySend("prtsc"); break;
		
		case R.id.Esc : keySend("Esc"); break;	case R.id.Window : keySend("Window"); break;
		case R.id.Kor_eng_toggle : keySend("Kor_eng_toggle"); break;
		
		//BackSpace, Space, Return(Enter), Del//->롱터치 이벤트에서 처리함.
		case R.id.BackSpace : break;	case R.id.Space : break;	case R.id.Return : break;
		case R.id.BackSpace2 : break;	case R.id.Space2 : break;	case R.id.Return2 : break;
		case R.id.BackSpace3 : break;	case R.id.Space3 : break;	case R.id.Return3 : break;
		case R.id.BackSpace4 : break;	case R.id.Space4 : break;	case R.id.Return4 : break;
		case R.id.BackSpace5 : break;	case R.id.Space5 : break;	case R.id.Return5 : break;
		case R.id.BackSpace6 : break;	case R.id.Space6 : break;	case R.id.Return6 : break;
		case R.id.BackSpace7 : break;	case R.id.Space7 : break;	case R.id.Return7 : break;
		case R.id.Del : break;
		//롱터치도 해주고 여기서도 해줘야 눌렷던게 풀림
		case R.id.Ctrl : keySend("Ctrl"); break;	case R.id.Ctrl2 : keySend("Ctrl"); break; 	
		case R.id.Ctrl3 : keySend("Ctrl"); break;	case R.id.Ctrl4 : keySend("Ctrl"); break;
		case R.id.Ctrl5 : keySend("Ctrl"); break;	case R.id.Ctrl6 : keySend("Ctrl"); break;
		case R.id.Ctrl7 : keySend("Ctrl"); break;
		case R.id.Alt : keySend("Alt"); break;		case R.id.Alt2 : keySend("Alt"); break;
		case R.id.Alt3 : keySend("Alt"); break;		case R.id.Alt4 : keySend("Alt"); break;
		case R.id.Alt5 : keySend("Alt"); break;		case R.id.Alt6 : keySend("Alt"); break;
		case R.id.Alt7 : keySend("Alt"); break;
		default : return false;
		}
		return true;
	}
	private void keySend(String keyBtn) {
		Thread Keyboard = new Thread(new KeyboardSend(keyBtn));
		Keyboard.start();
	}
}
