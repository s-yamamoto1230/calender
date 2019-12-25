package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import java.util.Calendar;
import java.sql.*;
import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


    //現在の日付取得
    Date today = new Date();
    //Calendarクラスのオブジェクト生成
    Calendar calendar = Calendar.getInstance();
    //現在の日付設定
    calendar.setTime(today);
    //年、月、日の取得
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    int day = calendar.get(Calendar.DATE);
    calendar.set(2019,11,1);
    int ww = calendar.get(Calendar.DAY_OF_WEEK)-1;
    //うるう年
    int a = year;
    int b = year;
    int c = year;
    int leap=0;
    if(a ==0 && b != 0 || c ==0){
      leap =29;
    }else{
      leap =28;
    int tuki_max;
    
    if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
      tuki_max =31;
    }else if(month==4 || month==6 || month==9 || month==11){
      tuki_max =30;
    }else{
      tuki_max =leap;
    }
  }

    int[] num = new int[37];
    Integer blank = 0;

    //配列へデータを入力
    for(int i=0;i<37;i++){
      if(ww>i){
        num[i] = blank;
      }else{
      num[i] = (i+1)-ww;
      }
    }
    for(int j=0;j<37;j++){
      if(num[j]>=32){
        num[j] = blank;
      }
    }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>メインページ</title>\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");
      out.write("  <ul id=\"nav\">\r\n");
      out.write("    <li><a href=\"#\" onclick=\"ShowAlert();\">ログアウト </a></li>\r\n");
      out.write("    <li><a href=\"addresscange.html\">メールアドレス変更</a></li>\r\n");
      out.write("    <li><a href=\"./ad_favodel.html\">お気に入り削除</a></li>\r\n");
      out.write("    <li><a href=\"./ad_del.html\">Agenda削除</a></li>\r\n");
      out.write("    <li><a href=\"./agenda_make.jsp?\">Agenda作成</a></li>\r\n");
      out.write("    <li><a href=\"./ag_search.html\">Agenda検索</a></li>\r\n");
      out.write("  </ul>\r\n");
      out.write("\r\n");
      out.write("  <table id=\"cal\">\r\n");
      out.write("\r\n");
      out.write("        <tr border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#CCCCCC\" style=\"font: 12px; color: #666666;\">\r\n");
      out.write("            <td align=\"center\" colspan=\"7\" bgcolor=\"#EEEEEE\" height=\"30\" style=\"color: #666666;\">\r\n");
      out.write("              <div class=\"tuki\">\r\n");
      out.write("                <button>前月</button>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"tuki\">\r\n");
      out.write("                <h1>");
      out.print( year );
      out.write('年');
      out.print( month );
      out.write("月</h1>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"tuki\">\r\n");
      out.write("               <button>翌月</button>\r\n");
      out.write("             </div>\r\n");
      out.write("            </div>\r\n");
      out.write("          </tr>\r\n");
      out.write("            <td align=\"center\" width=\"60\" height=\"30\" bgcolor=\"#FF3300\" style=\"color: #FFFFFF;\">日</td>\r\n");
      out.write("            <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"color: #666666;\">月</td>\r\n");
      out.write("            <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"color: #666666;\">火</td>\r\n");
      out.write("            <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"color: #666666;\">水</td>\r\n");
      out.write("            <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"color: #666666;\">木</td>\r\n");
      out.write("            <td align=\"center\" width=\"60\" bgcolor=\"#ffe4e1\" style=\"color: #666666;\">金</td>\r\n");
      out.write("            <td align=\"center\" width=\"60\" bgcolor=\"#00a1e9\" style=\"color: #666666;\">土</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            ");

            if (num[0]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFCC99\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-01\">\r\n");
      out.write("                ");
      out.print( num[0] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-01\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                  <h2>");
      out.print( num[0] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[1]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-02\">\r\n");
      out.write("                ");
      out.print( num[1] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-02\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[1] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[2]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-03\">\r\n");
      out.write("                ");
      out.print( num[2] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-03\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[2] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[3]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-04\">\r\n");
      out.write("                ");
      out.print( num[3] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-04\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[3] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[4]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-05\">\r\n");
      out.write("                ");
      out.print( num[4] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-05\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[4] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[5]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-06\">\r\n");
      out.write("                ");
      out.print( num[5] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-06\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[5] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-07\">\r\n");
      out.write("                ");
      out.print( num[6] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-07\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[6] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFCC99\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-08\">\r\n");
      out.write("                ");
      out.print( num[7] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-08\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[7] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-09\">\r\n");
      out.write("                ");
      out.print( num[8] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-09\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[8] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-10\">\r\n");
      out.write("                ");
      out.print( num[9] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-10\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[9] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-11\">\r\n");
      out.write("                ");
      out.print( num[10] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-11\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[10] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-12\">\r\n");
      out.write("                ");
      out.print( num[11] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-12\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[11] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-13\">\r\n");
      out.write("                ");
      out.print( num[12] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-13\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[12] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-14\">\r\n");
      out.write("                ");
      out.print( num[13] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-14\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[13] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFCC99\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-15\">\r\n");
      out.write("                ");
      out.print( num[14] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-15\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[14] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-16\">\r\n");
      out.write("                ");
      out.print( num[15] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-16\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[15] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-17\">\r\n");
      out.write("                ");
      out.print( num[16] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-17\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[16] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-18\">\r\n");
      out.write("                ");
      out.print( num[17] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-18\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[17] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-19\">\r\n");
      out.write("                ");
      out.print( num[18] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-19\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[18] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-20\">\r\n");
      out.write("                ");
      out.print( num[19] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-20\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[19] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-21\">\r\n");
      out.write("                ");
      out.print( num[20] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-21\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[20] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFCC99\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-22\">\r\n");
      out.write("                ");
      out.print( num[21] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-22\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[21] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-23\">\r\n");
      out.write("                ");
      out.print( num[22] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-23\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[22] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-24\">\r\n");
      out.write("                ");
      out.print( num[23] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-24\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[23] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-25\">\r\n");
      out.write("                ");
      out.print( num[24] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-25\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[24] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-26\">\r\n");
      out.write("                ");
      out.print( num[25] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-26\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[25] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-27\">\r\n");
      out.write("                ");
      out.print( num[26] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-27\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[26] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-28\">\r\n");
      out.write("                ");
      out.print( num[27] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-28\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[27] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFCC99\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-29\">\r\n");
      out.write("                ");
      out.print( num[28] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-29\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[28] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-30\">\r\n");
      out.write("                ");
      out.print( num[29] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-30\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[29] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-31\">\r\n");
      out.write("                ");
      out.print( num[30] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-31\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[30] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            if (num[31]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-32\">\r\n");
      out.write("                ");
      out.print( num[31] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-32\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[31] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[32]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-33\">\r\n");
      out.write("                ");
      out.print( num[32] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-33\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[32] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[33]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-34\">\r\n");
      out.write("                ");
      out.print( num[33] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-34\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[33] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[34]==0) {
            
      out.write("\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-35\">\r\n");
      out.write("                ");
      out.print( num[34] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-35\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[34] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("          ");

          if (num[35]==0) {
            
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFCC99\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-36\">\r\n");
      out.write("                ");
      out.print( num[35] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-36\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[35] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("            ");

            if (num[36]==0) {
            
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

            }else{
            
      out.write("\r\n");
      out.write("            <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"color: #666666;\">\r\n");
      out.write("              <a href=\"#modal-37\">\r\n");
      out.write("                ");
      out.print( num[36] );
      out.write("\r\n");
      out.write("              </a>\r\n");
      out.write("              <div class=\"modal-wrapper\" id=\"modal-37\">\r\n");
      out.write("                <a href=\"#!\" class=\"modal-overlay\"></a>\r\n");
      out.write("                <div class=\"modal-window\">\r\n");
      out.write("                  <div class=\"modal-content\">\r\n");
      out.write("                <h2>");
      out.print( num[36] );
      out.write("日の予定</h2>\r\n");
      out.write("                  <p>学校</p>\r\n");
      out.write("                  <p>バイト</p>\r\n");
      out.write("                  <a href=\"./schedule_make.jsp\"><button>追加</button></a>\r\n");
      out.write("                  <a href=\"#!\" class=\"modal-close\">×</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");

            }
            
      out.write("\r\n");
      out.write("          </tr>\r\n");
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("\r\n");
      out.write("  <script type=\"text/javascript\" src=\"./js/main.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
