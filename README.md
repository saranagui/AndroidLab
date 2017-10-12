# AndroidLab
Using Android Studio

Lab 2 Tasks:
- Using layouts, Create a UI that contains 3 sections. Topright, Topleft and, bottom.
- The top right section should contain a webview element which initially loads the MET website.
- The top left section should include a text area and a button.
	The user can type in a comment and press "add comment" to append his comment(s) below the 	 text area.
	Moreover, when the button is clicked the webview element should be updated with the most 	recently posed comment.
- The bottom section contains the android logo on the left and a gray placeholder on the right.
	When the user drags the logo onto the placeholder, a new UI should be shown (**not** a new 		activity)
		The new UI looks exactly like the old UI except for the bottom section (See how 		you can use include or inherit parts of layouts. Hint: <merge> and <include>).
		This new layout has a green background.
		In the bottom section of this new UI:
		When the user swipes down, The background should be dimmed.
		When the user swipes up, The background should be made lighter
		When the user double-taps, The old UI should be shown again. However, with a 			different background. (Hint: View flipper, viewtree observer)
