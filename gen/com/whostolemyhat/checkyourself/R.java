/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package com.whostolemyhat.checkyourself;

public final class R {
    public static final class attr {
        /** <p>Must be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static final int cb_color=0x7f010000;
        /** <p>Must be a dimension value, which is a floating point number appended with a unit such as "<code>14.5sp</code>".
Available units are: px (pixels), dp (density-independent pixels), sp (scaled pixels based on preferred font size),
in (inches), mm (millimeters).
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static final int cb_pressed_ring_width=0x7f010001;
    }
    public static final class color {
        public static final int blue=0x7f060000;
        public static final int dark_blue=0x7f060002;
        public static final int dark_green=0x7f060004;
        public static final int green=0x7f060003;
        public static final int light_blue=0x7f060001;
        public static final int text_grey=0x7f060005;
        public static final int yellow=0x7f060006;
    }
    public static final class dimen {
        /**  Default screen margins, per the Android Design guidelines. 

         Customize dimensions originally defined in res/values/dimens.xml (such as
         screen margins) for sw720dp devices (e.g. 10" tablets) in landscape here.
    
         */
        public static final int activity_horizontal_margin=0x7f040000;
        public static final int activity_vertical_margin=0x7f040001;
    }
    public static final class drawable {
        public static final int blue_button_text=0x7f020000;
        public static final int button_background=0x7f020001;
        public static final int button_focus=0x7f020002;
        public static final int button_pressed=0x7f020003;
        public static final int button_text=0x7f020004;
        public static final int heart=0x7f020005;
        public static final int heart_clear=0x7f020006;
        public static final int ic_action_alarms=0x7f020007;
        public static final int ic_action_discard=0x7f020008;
        public static final int ic_action_edit=0x7f020009;
        public static final int ic_action_new=0x7f02000a;
        public static final int ic_launcher=0x7f02000b;
        public static final int round_button=0x7f02000c;
    }
    public static final class id {
        public static final int DatePicker=0x7f090006;
        public static final int DateTimePicker=0x7f090005;
        public static final int TimePicker=0x7f090007;
        public static final int add=0x7f090004;
        public static final int alarmLabel=0x7f090008;
        public static final int alarm_list=0x7f090000;
        public static final int alarm_time=0x7f090009;
        public static final int delete=0x7f090003;
        public static final int label=0x7f090002;
        public static final int set_notification=0x7f09000a;
        public static final int time=0x7f090001;
        public static final int view_alarms=0x7f09000b;
    }
    public static final class layout {
        public static final int alarm_list=0x7f030000;
        public static final int alarm_list_view=0x7f030001;
        public static final int date_time_dialog=0x7f030002;
        public static final int label_dialog=0x7f030003;
        public static final int main_view=0x7f030004;
    }
    public static final class menu {
        public static final int main_activity_actions=0x7f080000;
    }
    public static final class string {
        public static final int action_settings=0x7f050001;
        public static final int add_alarm=0x7f05000c;
        public static final int after_meal=0x7f050006;
        public static final int alarm_time=0x7f050003;
        public static final int alarm_title=0x7f050004;
        public static final int app_name=0x7f050000;
        public static final int button_title=0x7f050002;
        public static final int default_alarm=0x7f05000a;
        public static final int delete_alarm=0x7f05000b;
        public static final int label_dialog_title=0x7f05000d;
        public static final int manual=0x7f050007;
        public static final int set_alarm=0x7f050005;
        public static final int set_label=0x7f050009;
        public static final int view_alarms=0x7f050008;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.
    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.
        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static final int AppBaseTheme=0x7f070000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static final int AppTheme=0x7f070001;
    }
    public static final class styleable {
        /** Attributes that can be used with a CircleButton.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #CircleButton_cb_color com.whostolemyhat.checkyourself:cb_color}</code></td><td></td></tr>
           <tr><td><code>{@link #CircleButton_cb_pressed_ring_width com.whostolemyhat.checkyourself:cb_pressed_ring_width}</code></td><td></td></tr>
           </table>
           @see #CircleButton_cb_color
           @see #CircleButton_cb_pressed_ring_width
         */
        public static final int[] CircleButton = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>This symbol is the offset where the {@link com.whostolemyhat.checkyourself.R.attr#cb_color}
          attribute's value can be found in the {@link #CircleButton} array.


          <p>Must be a color value, in the form of "<code>#<i>rgb</i></code>", "<code>#<i>argb</i></code>",
"<code>#<i>rrggbb</i></code>", or "<code>#<i>aarrggbb</i></code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name com.whostolemyhat.checkyourself:cb_color
        */
        public static final int CircleButton_cb_color = 0;
        /**
          <p>This symbol is the offset where the {@link com.whostolemyhat.checkyourself.R.attr#cb_pressed_ring_width}
          attribute's value can be found in the {@link #CircleButton} array.


          <p>Must be a dimension value, which is a floating point number appended with a unit such as "<code>14.5sp</code>".
Available units are: px (pixels), dp (density-independent pixels), sp (scaled pixels based on preferred font size),
in (inches), mm (millimeters).
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name com.whostolemyhat.checkyourself:cb_pressed_ring_width
        */
        public static final int CircleButton_cb_pressed_ring_width = 1;
    };
}
