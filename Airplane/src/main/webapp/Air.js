function openPopUp(PT_no) {
	window.name = "parentPage";
	let openWin = window.open("pop1.jsp?ticket_no=" + PT_no, "popup1", "width=450, height=250, top=150, left=200");
	// 함수 동작 테스트 
	//alert("팝업 테스트");
	//window.open("[팝업을 띄울 파일명 path]", "[별칭]", "[팝업 옵션]")

}

function closePopup() {
	//함수 동작 테스트
	//alert("click");

	opener.name = "parentPage";
	var f = document.forms.popupForm2;
	f.target = opener.name;
	f.submit();


	//자신(팝업)을 종료한다.
	self.close();
}