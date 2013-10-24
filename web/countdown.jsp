<script type="text/javascript">		
/*
    Count down until any date script-
*/

/*  
CHANGE THE ITEMS BELOW TO CREATE YOUR COUNTDOWN TARGET DATE AND ANNOUNCEMENT 
ONCE THE TARGET DATE AND TIME ARE REACHED.
*/
var note="No Contest At Present!";	/* -->Enter what you want the script to 
				              display when the target date and time 
				              are reached, limit to 25 characters */
<%@page import="za.co.zs6erb.model.Contest"%>
<%@page import="za.co.zs6erb.dao.ContestDao"%>
<%
    //Look for active contest
    ContestDao cdao = new ContestDao();
    int contId = cdao.getcurrentContestID();
    System.out.println("ContestID = " + contId);
    
    if (contId > 0) {
        Contest contest = cdao.getContestById(contId);
%>

var year=<%=contest.getEndYear()%>;      /* -->Enter the count down target date YEAR */
var month=<%=contest.getEndMonth()%>;       /* -->Enter the count down target date MONTH */
var day=<%=contest.getEndDay()%>;         /* -->Enter the count down target date DAY */
var hour=<%=contest.getEndHour()%>;         /* -->Enter the count down target date HOUR (24 hour clock) */
var minute=<%=contest.getEndMin()%>;      /* -->Enter the count down target date MINUTE */

var tz=+2;          /* -->Offset for your timezone in hours from UTC (see 
			  http://wwp.greenwichmeantime.com/index.htm to find 
			  the timezone offset for your location) */
<%
    }
    else {
%> document.getElementById("holder").style.display="none" <% } %>

//-->    DO NOT CHANGE THE CODE BELOW!    <--	
d1 = new Image(); d1.src = "digital-numbers/1.png";
d2 = new Image(); d2.src = "digital-numbers/2.png";
d3 = new Image(); d3.src = "digital-numbers/3.png";
d4 = new Image(); d4.src = "digital-numbers/4.png";
d5 = new Image(); d5.src = "digital-numbers/5.png";
d6 = new Image(); d6.src = "digital-numbers/6.png";
d7 = new Image(); d7.src = "digital-numbers/7.png";
d8 = new Image(); d8.src = "digital-numbers/8.png";
d9 = new Image(); d9.src = "digital-numbers/9.png";
d0 = new Image(); d0.src = "digital-numbers/0.png";
bkgd = new Image(); bkgd.src = "digital-numbers/bkgd.gif";

var montharray=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");

function countdown(yr,m,d,hr,min){
	theyear=yr;themonth=m;theday=d;thehour=hr;theminute=min;
	var today=new Date();
	var todayy=today.getYear();
	if (todayy < 1000) {todayy+=1900;}
	var todaym=today.getMonth();
	var todayd=today.getDate();
	var todayh=today.getHours();
	var todaymin=today.getMinutes();
	var todaysec=today.getSeconds();
	var todaystring1=montharray[todaym]+" "+todayd+", "+todayy+" "+todayh+":"+todaymin+":"+todaysec;
	var todaystring=Date.parse(todaystring1)+(tz*1000*60*60);
	var futurestring1=(montharray[m-1]+" "+d+", "+yr+" "+hr+":"+min);
	var futurestring=Date.parse(futurestring1)-(today.getTimezoneOffset()*(1000*60));
	var dd=futurestring-todaystring;
	var dday=Math.floor(dd/(60*60*1000*24)*1);
	var dhour=Math.floor((dd%(60*60*1000*24))/(60*60*1000)*1);
	var dmin=Math.floor(((dd%(60*60*1000*24))%(60*60*1000))/(60*1000)*1);
	var dsec=Math.floor((((dd%(60*60*1000*24))%(60*60*1000))%(60*1000))/1000*1);
	if(dday<=0&&dhour<=0&&dmin<=0&&dsec<=0){
		document.getElementById('note').innerHTML=note;
		document.getElementById('note').style.display="block";
		document.getElementById('countdown').style.display="none";
		clearTimeout(startTimer);
		return;
	}
	else {
		document.getElementById('note').style.display="none";
		document.getElementById('timer').style.display="block";
		startTimer = setTimeout("countdown(theyear,themonth,theday,thehour,theminute)",500);
	}
	convert(dday,dhour,dmin,dsec);
}

function convert(d,h,m,s) {
	if (!document.images) return;
	if (d <= 9) {
		document.images.day1.src = bkgd.src;
		document.images.day2.src = bkgd.src;
		document.images.day3.src = eval("d"+d+".src");
	}
	else if (d <= 99) {
		document.images.day1.src = bkgd.src;
		document.images.day2.src = eval("d"+Math.floor(d/10)+".src");
		document.images.day3.src = eval("d"+(d%10)+".src");
	}
	else {
		document.images.day1.src = eval("d"+Math.floor(d/100)+".src");
		var day = d.toString();
		day = day.substr(1,1);
		day = parseInt(day);
		document.images.day2.src = eval("d"+day+".src");
		document.images.day3.src = eval("d"+(d%10)+".src");
	}
	if (h <= 9) {
		document.images.h1.src = d0.src;
		document.images.h2.src = eval("d"+h+".src");
	}
	else {
		document.images.h1.src = eval("d"+Math.floor(h/10)+".src");
		document.images.h2.src = eval("d"+(h%10)+".src");
	}
	if (m <= 9) {
		document.images.m1.src = d0.src;
		document.images.m2.src = eval("d"+m+".src");
	}
	else {
		document.images.m1.src = eval("d"+Math.floor(m/10)+".src");
		document.images.m2.src = eval("d"+(m%10)+".src");
	}
	if (s <= 9) {
		document.images.s1.src = d0.src;
		document.images.s2.src = eval("d"+s+".src");
	}
	else {
		document.images.s1.src = eval("d"+Math.floor(s/10)+".src");
		document.images.s2.src = eval("d"+(s%10)+".src");
	}
}
</script>
