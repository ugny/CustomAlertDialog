package com.ugunay.customalertdialog;

import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.Contract;

/**
 * Bu sınıf <code>CustomAlertDialog</code> sınıfının kullanıma sunduğu dialog pencerelerini
 * farklı kombinasyonlarda ve tasarımlarda <b>test</b> etmek için yazılmıştır.
 */
class Tester {

    private final AppCompatActivity activity;
    private final int ICON;

    /**
     * Constructor method.
     *
     * @param activity parent activity.
     */
    public Tester(@NonNull AppCompatActivity activity) {
        this.activity = activity;
        ICON = R.drawable.ic_report;
    }

    /**
     * Bütün test fonksiyonlarını sırasıyla çağırır.
     */
    public void testDialogs() {
        setItems();
        testDefaultDialog();
        setColors();

        testMultiChoiceItems();
        testSingleChoiceItems();
        testItems();

        testMultipleButtonDesign();
        testVerticalButtons();
        testOneButton();
        testCustomDialogs();
        testHtmlMessage();
        testSimpleDialogs();
        testPrepareDialogs();
        testImageButtons();
    }

    /**
     * CustomAlertDialog sınıfının varsayılan renklerini kullanarak alert dialog örnekleri oluşturur.
     * Bu örneğin çalışabilmesi için bu metot setColors() metodundan önce çağrılmıştır.
     */
    private void testDefaultDialog() {
        testSingleChoiceItems();
        testMultipleButtonDesign();
        testVerticalButtons();
        testOneButton();
        testHtmlMessage();
        testSimpleDialogs();
        testPrepareDialogs();
        testImageButtons();

        String message = "CustomAlertDialog kütüphanesinin varsayılan değerleriyle oluşturulmuş " +
                "bir alert dialog örneğidir. Yazılımcı sınıf değişkenlerine herhangi bir atama " +
                "yapmadığında varsayılan değerler geçerli olacaktır.";
        CustomAlertDialog alertDialog = CustomAlertDialog.getInstance(activity);
        alertDialog.setIcon(ICON)
                .setMessage(message)
                .setPositiveButton("Onayla", dialog -> {
                    showToast("Pozitif butona basıldı.");
                    dialog.dismiss();
                })
                .setNegativeButton("İptal", dialog -> {
                    showToast("Negatif butona basıldı.");
                    dialog.dismiss();
                })
                .show();
    }

    /**
     * CustomAlertDialog sınıfının istenilen özelliklerini set eder. Yazılımcı projenin başında
     * renkleri bir kez tanımlayarak projenin her yerinde aynı tasarımı kullanabilir.
     */
    private void setColors() {
        CustomAlertDialog.setBackgroundColor(
                activity.getResources().getColor(R.color.cad_dialog_bg_color));
        CustomAlertDialog.setMessageTextColor(Color.parseColor("#EDEDED"));
        CustomAlertDialog.setPositiveButtonBackgroundColor(
                activity.getResources().getColor(R.color.cad_positive_button_bg_color));
        CustomAlertDialog.setNegativeButtonStrokeColor(
                activity.getResources().getColor(R.color.cad_negative_button_stroke_color));

        CustomAlertDialog.setFontResId(R.font.titillium_regular);
        CustomAlertDialog.setInfoColor(Color.CYAN);
    }

    /**
     * Hazır alert dialog tasarımları gösterir. Sadece <b>mesaj</b> bilgisini dışarıdan alarak
     * pratik bir kullanm sunar. Bir tek warning alert dialogu ekstra olarak pozitif butona ait
     * <code>title</code> ve <code>OnClickListener</code> nesnesini dışarıdan alır. İlk üç alert
     * dialog pencereden çıkış için <b>Tamam</b> butonu bulundurur.
     */
    private void testPrepareDialogs() {
        String message = "showPreparedDialog(@NonNull String message, int messageTextColor, " +
                "int iconResId, int iconTintAndTextPositiveButtonTextColor) fonksiyonu: " +
                "İkon tint ve text pozitif butonun metin rengi aynı olan hazır bir alert dialog " +
                "tasarımıdır. Bu örnekte mesaj metin rengi de aynı verilmiştir.";
        CustomAlertDialog.getInstance(activity).showPreparedDialog(message,
                activity.getResources().getColor(R.color.purple_200), R.drawable.ic_report,
                activity.getResources().getColor(R.color.purple_200));

        String message_0 = "showPreparedDialog(@NonNull String message, int iconResId, " +
                "int iconTintAndTextPositiveButtonTextColor) fonksiyonu: İkon tint ve text pozitif " +
                "butonun metin rengi aynı olan hazır bir alert dialog tasarımıdır.";
        CustomAlertDialog.getInstance(activity).showPreparedDialog(message_0, R.drawable.ic_report,
                activity.getResources().getColor(R.color.purple_200));

        String message_1 = "showInfoDialog(@NonNull String message) fonksiyonu: Kullanıcıya herhangi " +
                "bir konuda bilgilendirme yapmak için kullanılan hazır bir alert dialog tasarımıdır. " +
                "Sadece mesaj bilgisini parametre olarak alır.";
        CustomAlertDialog.getInstance(activity).showInfoDialog(message_1);

        String message_2 = "showSuccessDialog(@NonNull String message) fonksiyonu: " +
                "Kullanıcıya başarılı bir işlem sonucunu bildirmek için kullanılan hazır " +
                "bir alert dialog tasarımıdır. Sadece mesaj bilgisini parametre olarak alır.";
        CustomAlertDialog.getInstance(activity).showSuccessDialog(message_2);

        String message_3 = "showErrorDialog(@NonNull String message) fonksiyonu: Kullanıcıya " +
                "hatalı bir işlem sonucunu bildirmek için kullanılan hazır bir alert dialog " +
                "tasarımıdır. Sadece mesaj bilgisini parametre olarak alır.";
        CustomAlertDialog.getInstance(activity).showErrorDialog(message_3);

        String message_4 = "showWarningDialog(@NonNull String message, @NonNull String " +
                "positiveButtonTitle, @NonNull OnClickListener positiveButtonOnClickListener) " +
                "fonksiyonu: Kullanıcıya herhangi bir konuda uyarıda bulunmak için kullanılan " +
                "hazır bir alert dialog tasarımıdır. Mesaj, pozitif buton title ve pozitif butona " +
                "tıklama olayını parametre olarak alır.";
        CustomAlertDialog.getInstance(activity).showWarningDialog(message_4,
                activity.getString(R.string.go_on), CustomAlertDialog::dismiss);
    }

    /**
     * Kullanımı pratik olan ve sade tasarımlı alert dialoglar oluşturur.
     */
    private void testSimpleDialogs() {
        String message_1 = "Sade tasarımda olan bir alert dialog örneğidir. " +
                "Pozitif buton setSimplePositiveButton() ve negatif buton setSimpleNegativeButton() " +
                "fonksiyonları kullanılarak tanımlanmıştır. Pozitif ve negatif butonların metin " +
                "renkleri istenirse örnekte de olduğu gibi farklı verilebilir. " +
                "Verilmezse yazılımcının seçtiği renklere göre varsayılan rekler geçerli olacaktır. " +
                "Pozitif ve negatif butonların başlıklarının kısa olması tavsiye edilir.";
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage(message_1)
                .setTextPositiveButton("Onayla", CustomAlertDialog::dismiss,
                        activity.getResources().getColor(R.color.cad_success_color))
                .setTextNegativeButton("Bunu bir daha gösterme", CustomAlertDialog::dismiss,
                        activity.getResources().getColor(R.color.cad_error_color))
                .show();

        String message_2 = "showSimpleDialog(@NonNull String message, int positiveButtonTextColor) " +
                "fonksiyonu: Mesaj bilgisini ve 'Tamam' butonunun metin rengini parametre olarak " +
                "alan hazır bir alert dialog tasarımdır. Bu örnekte pozitif buton metin rengi " +
                "farklı verilmiştir.";
        CustomAlertDialog.getInstance(activity).showSimpleDialog(message_2, Color.GREEN);

        String message_3 = "showSimpleDialog(@NonNull String message) fonksiyonu: Sadece mesaj " +
                "bilgisini parametre olarak alan hazır bir alert dialog tasarımdır. Eğer farklı " +
                "bir atama yapılmadıysa 'Tamam' butonunun metin rengi mesajın metin rengiyle aynıdır. ";
        CustomAlertDialog.getInstance(activity).showSimpleDialog(message_3);
    }

    /**
     * <b>Html</b> içerikli mesaj gösteren alert dialog örnekleri oluşturur.
     */
    private void testHtmlMessage() {
        String htmlMessage = "<p><b>HTML</b> metin örneği</p>" +
                "İnanan kullarıma söyle, <font color=\"#E6BD9A\"><b>namazı dosdoğru kılsınlar</b>" +
                "</font>, hiçbir alışveriş ve dostluğun bulunmadığı bir gün gelmeden önce " +
                "<font color=\"#E6BD9A\">kendilerine rızık olarak verdiğimiz şeylerden <b>Allah yolunda</b> " +
                "gizlice ve açıktan <b>harcasınlar.</b></font> ﴾İbrâhîm 31﴿";
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessageFromHtml(htmlMessage)
                .setPositiveButton("Okudum", DialogFragment::dismiss)
                .setNegativeButton("Kapat", DialogFragment::dismiss)
                .show();

        String htmlMessage_2 = "<p>showSimpleDialogFromHtml(@NonNull String <b>htmlMessage</b>) " +
                "fonksiyonu:</p> Sade tasarımlı <font color=\"#E6BD9A\"><b>HTML</b> formatında</font> " +
                "<b>mesaj</b> gösterebilen hazır bir alert dialog tasarımıdır. Bu tasarım pencereyi " +
                "<b>kapatmak</b> için <b>'Tamam'</b> butonu barındırır.";
        CustomAlertDialog.getInstance(activity).showSimpleDialogFromHtml(htmlMessage_2);
    }

    /**
     * Farklı renklere ve kombinasyonlara sahip alert dialog örnekleri oluşturur.
     */
    private void testCustomDialogs() {
        String message_1 = "Sadece bu alert dialog için bütün özellikleri değiştirilmiş bir alert " +
                "dialog örneğidir. Mesajın typeface özelliği de değiştirilmiştir. Cancelable " +
                "özelliği false yapılmıştır. Aynı zamanda buton metinlerinin satır sayısının " +
                "farklı olduğu durum da test edilmiştir. Bu durumda butonların boyutları da farklı " +
                "olmaktadır. Bu sebeple yazılımcı dialog pencerelerinde olması gerektiği gibi " +
                "buton metinlerini kısa seçmelidir. Uzun metinli buton veya ikonlu buton kullanılmak " +
                "istenirse setVerticalButtons() fonksiyonu kullanılabilir.";
        CustomAlertDialog alertDialog_1 = CustomAlertDialog.getInstance(activity);
        alertDialog_1.setCancelable(false);
        alertDialog_1.setBackground(Color.parseColor("#415569"))
                .setIcon(ICON, Color.GREEN)
                .setMessage(message_1, Color.YELLOW, R.font.imprima)
                .setPositiveButton("Paylaşma penceresine git", dialog -> {
                    showToast("Pozitif butona basıldı.");
                    dialog.dismiss();
                }, activity.getResources().getColor(R.color.teal_200), Color.BLUE)
                .setNegativeButton("İptal", dialog -> {
                    showToast("Negatif butona basıldı.");
                    dialog.dismiss();
                }, Color.parseColor("#458699"), Color.RED)
                .show();

        String message_2 = "Bütün UI elemanları kullanılmış fakat hiçbir renk ataması yapılmamış " +
                "bir alert dialog örneğidir. Bu örneğin amacı yazılımcının seçtiği bütün renkleri " +
                "test etmek içindir.";
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage(message_2)
                .setPositiveButton("Onayla", dialog -> {
                    showToast("Pozitif butona basıldı.");
                    dialog.dismiss();
                })
                .setNegativeButton("İptal", dialog -> {
                    showToast("Negatif butona basıldı.");
                    dialog.dismiss();
                })
                .show();
    }

    /**
     * Tek butonlu alert dialog örnekleri oluşturur.
     */
    private void testOneButton() {
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("Sadece negatif butonun bulunduğu bir dialog örneğidir.")
                .setNegativeButton("Silme işlemini başlat", DialogFragment::dismiss)
                .show();

        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("Sadece pozitif butonun bulunduğu bir dialog örneğidir.")
                .setPositiveButton("Seçim penceresine git", DialogFragment::dismiss)
                .show();
    }

    /**
     * VerticalButtons tasarımının örneklerini gösteren alert dialog örnekleri oluşturur.
     */
    private void testVerticalButtons() {
        String message_1 = "Butonların dikey olarak sıralandığı bir alert dialog örneğidir. " +
                "Bu örnekte ikon ataması da yapılmıştır. İkon ataması yapıldığı zaman textView'in " +
                "gravity özelliği centerVertical yapılmaktadır.";
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage(message_1)
                .setVerticalButtons("Paylaşma penceresine git", DialogFragment::dismiss,
                        android.R.drawable.ic_menu_share, "İptal ediyorum",
                        DialogFragment::dismiss, android.R.drawable.ic_menu_delete)
                .show();

        String message_2 = "Butonların dikey olarak sıralandığı bir alert dialog örneğidir. " +
                "Uzun metinli veya ikonlu butonlar kullanılmak istendiğinde bu tasarım tercih " +
                "edilebilir. Bu tasarım için setVerticalButtons() fonksiyonu kullanılır. " +
                "Bu tasarımda pozitif butonla birlikte negatif buton da olmak zorundadır. " +
                "Yoksa dikey buton tasarımının bir anlamı kalmayacaktır. Zira tek butonun " +
                "olduğu tasarım setNegativeButton() veya setPositiveButton() fonksiyonlarından " +
                "biri kullanılarak da elde edilebilir.";
        CustomAlertDialog.getInstance(activity)
                .setMessage(message_2)
                .setVerticalButtons("Yükleme işlemine geç", DialogFragment::dismiss,
                        "İptal ediyorum", DialogFragment::dismiss)
                .show();
    }

    /**
     * Farklı buton tasarımlarının aynı anda kullanıldığı alert dialog örnekleri oluşturur.
     */
    private void testMultipleButtonDesign() {
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("Bütün buton tasarımlarının aynı anda " +
                        "kullanıldığı bir alert dialog örneğidir.")
                .setPositiveButton("Kaydet", DialogFragment::dismiss)
                .setNegativeButton("Sil", DialogFragment::dismiss)
                .setTextPositiveButton("Güzel", DialogFragment::dismiss)
                .setTextNegativeButton("Fena değil", DialogFragment::dismiss)
                .setVerticalButtons("Paylaşma penceresine git", DialogFragment::dismiss,
                        android.R.drawable.ic_menu_share, "İptal ediyorum",
                        DialogFragment::dismiss, android.R.drawable.ic_menu_delete)
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle,
                                R.drawable.cad_ic_error,
                                R.drawable.cad_ic_warning,
                                R.drawable.cad_ic_info,
                                ICON},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı."),
                                dialog -> showToast("2. Image Button tıklandı."),
                                dialog -> showToast("3. Image Button tıklandı."),
                                dialog -> showToast("4. Image Button tıklandı."),
                                null
                        })
                .show();

        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("Pozitif butonla textPositive ve textNegative butonların aynı anda " +
                        "kullanıldığı bir alert dialog örneğidir.")
                .setPositiveButton("Kaydet", DialogFragment::dismiss)
                .setTextPositiveButton("Güzel", DialogFragment::dismiss)
                .setTextNegativeButton("Fena değil", DialogFragment::dismiss)
                .show();

        CustomAlertDialog.getInstance(activity)
                .setMessage("4 tane image butonla sadece negatif butonun aynı anda " +
                        "kullanıldığı bir alert dialog örneğidir.")
                .setNegativeButton("Pencereyi kapat", DialogFragment::dismiss)
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle,
                                R.drawable.cad_ic_error,
                                R.drawable.cad_ic_warning,
                                R.drawable.cad_ic_info},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı."),
                                dialog -> showToast("2. Image Button tıklandı."),
                                dialog -> showToast("3. Image Button tıklandı."),
                                dialog -> showToast("4. Image Button tıklandı.")
                        })
                .show();

        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("3 tane image butonla pozitif ve negatif butonların aynı anda " +
                        "kullanıldığı bir alert dialog örneğidir.")
                .setPositiveButton("Kaydet", DialogFragment::dismiss)
                .setNegativeButton("Sil", DialogFragment::dismiss)
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle,
                                R.drawable.cad_ic_error,
                                R.drawable.cad_ic_warning},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı."),
                                dialog -> showToast("2. Image Button tıklandı."),
                                dialog -> showToast("3. Image Button tıklandı.")
                        })
                .show();

        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("Vertical butonlar ile pozitif ve negatif butonların aynı anda " +
                        "kullanıldığı bir alert dialog örneğidir.")
                .setPositiveButton("Kaydet", DialogFragment::dismiss)
                .setNegativeButton("Sil", DialogFragment::dismiss)
                .setVerticalButtons("Paylaşma penceresine git", DialogFragment::dismiss,
                        android.R.drawable.ic_menu_share, "İptal ediyorum",
                        DialogFragment::dismiss, android.R.drawable.ic_menu_delete)
                .show();

        CustomAlertDialog.getInstance(activity)
                .setMessage("Vertical butonlar ile negatif butonun aynı anda " +
                        "kullanıldığı bir alert dialog örneğidir.")
                .setNegativeButton("Sil", DialogFragment::dismiss)
                .setVerticalButtons("Yükleme işlemine geç", DialogFragment::dismiss,
                        "İptal ediyorum", DialogFragment::dismiss)
                .show();
    }

    /**
     * ImageButtons tasarımının örneklerini gösteren alert dialog örnekleri oluşturur.
     */
    private void testImageButtons() {
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("Tüm image butonların kullanıldığı bir örnektir.")
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle,
                                R.drawable.cad_ic_error,
                                R.drawable.cad_ic_warning,
                                R.drawable.cad_ic_info,
                                ICON},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı."),
                                dialog -> showToast("2. Image Button tıklandı."),
                                dialog -> showToast("3. Image Button tıklandı."),
                                dialog -> showToast("4. Image Button tıklandı."),
                                null
                        })
                .show();

        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("4 image butonun kullanıldığı bir örnektir.")
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle,
                                R.drawable.cad_ic_error,
                                R.drawable.cad_ic_warning,
                                R.drawable.cad_ic_info},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı."),
                                dialog -> showToast("2. Image Button tıklandı."),
                                dialog -> showToast("3. Image Button tıklandı."),
                                null
                        })
                .show();

        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON)
                .setMessage("3 image butonun kullanıldığı bir örnektir.")
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle,
                                R.drawable.cad_ic_error,
                                R.drawable.cad_ic_warning},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı."),
                                dialog -> showToast("2. Image Button tıklandı."),
                                null
                        })
                .show();

        CustomAlertDialog.getInstance(activity)
                .setMessage("2 image butonun kullanıldığı bir örnektir.")
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle,
                                R.drawable.cad_ic_error},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı."),
                                dialog -> showToast("2. Image Button tıklandı.")
                        })
                .show();

        CustomAlertDialog.getInstance(activity)
                .setMessage("1 image butonun kullanıldığı bir örnektir.")
                .setImageButtons(new int[]{
                                R.drawable.cad_ic_check_circle},
                        new CustomAlertDialog.OnClickListener[]{
                                dialog -> showToast("1. Image Button tıklandı.")
                        })
                .show();
    }

    /**
     * CharSequence listesini (items) gösteren alert dialog örnekleri oluşturur.
     */
    private void testItems() {
        String message_1 = "Sizi en iyi hangisi anlatır?";
        CustomAlertDialog.getInstance(activity)
                .setIcon(ICON, Color.GREEN)
                .showItems(message_1, smallItems, (dialog, which) -> {
                    showToast("Tıklanan eleman: " + items[which]);
                    dialog.dismiss();
                });

        String message_2 = "İkonsuz ve uzun listeli bir alert dialog örneğidir. Bu tasarımla " +
                "kullanıcıdan listeden herhangi bir elemanı seçmesi istenebilir. Kullanıcının " +
                "pencereyi kapatabilmesi için negatif buton hazır olarak bulunmaktadır. Ayrıca " +
                "bu örnekte cancelable özelliği false yapılmıştır.";
        CustomAlertDialog alertDialog = CustomAlertDialog.getInstance(activity);
        alertDialog.setCancelable(false);
        alertDialog.showItems(message_2, items, (dialog, which) -> {
            showToast("Tıklanan eleman: " + items[which]);
            dialog.dismiss();
        });
    }

    /**
     * CharSequence listesi (items) üzerinde sadece bir elemanın seçim işleminin yapılabileceği
     * alert dialog örnekleri oluşturur.
     */
    private void testSingleChoiceItems() {
        String message_1 = "Metni paylaşmak için bir yöntem seçiniz:";
        CustomAlertDialog.getInstance(activity)
                .setIcon(android.R.drawable.ic_menu_share)
                .showSingleChoiceItems(message_1, smallItems, 15, (dialog, which) -> {
                    showToast("Tıklanan eleman: " + items[which]);
                }, "Paylaş", DialogFragment::dismiss);

        String message_2 = "İkonsuz ve uzun listeli bir alert dialog örneğidir. Bu tasarımla " +
                "kullanıcıdan listeden herhangi bir elemanı seçmesi istenebilir. Seçme işleminin " +
                "tamamlanması için kullanıcının pozitif butona basması gerekmektedir. Kullanıcının " +
                "pencereyi kapatabilmesi için negatif buton hazır olarak bulunmaktadır.";
        CustomAlertDialog.getInstance(activity)
                .showSingleChoiceItems(message_2, items, 5, (dialog, which) -> {
                    showToast("Tıklanan eleman: " + items[which]);
                }, "Lorem", DialogFragment::dismiss);
    }

    /**
     * CharSequence listesi (items) üzerinde çoklu eleman seçim işleminin yapılabileceği
     * alert dialog örnekleri oluşturur.
     */
    private void testMultiChoiceItems() {
        boolean[] checkedItems = new boolean[items.length];
        for (int i = 0; i < checkedItems.length; i++) {
            checkedItems[i] = i % 5 == 0;
        }

        String message_1 = "Hobilerinizi seçebilirsiniz:";
        CustomAlertDialog.getInstance(activity)
                .setIcon(android.R.drawable.checkbox_on_background)
                .showMultiChoiceItems(message_1, smallItems, checkedItems, (dialog, which, isChecked) -> {
                    showToast("Tıklanan eleman: " + items[which] + " - " + isChecked);
                }, "Seçtim", DialogFragment::dismiss);

        String message_2 = "İkonsuz ve uzun listeli bir alert dialog örneğidir. Bu tasarımla " +
                "kullanıcıdan listeden çoklu seçim işlemi yapması istenebilir. Seçme işleminin " +
                "tamamlanması için kullanıcının pozitif butona basması gerekmektedir. Kullanıcının " +
                "pencereyi kapatabilmesi için negatif buton hazır olarak bulunmaktadır.";
        CustomAlertDialog.getInstance(activity)
                .showMultiChoiceItems(message_2, items, checkedItems, (dialog, which, isChecked) -> {
                    showToast("Tıklanan eleman: " + items[which] + " - " + isChecked);
                }, "Lorem", DialogFragment::dismiss);
    }


    private CharSequence[] smallItems;
    private CharSequence[] items;

    /**
     * items dizisini test dialoglar için doldurur.
     */
    private void setItems() {
        smallItems = getItems(10);
        items = getItems(50);
    }

    @NonNull
    @Contract(pure = true)
    private CharSequence[] getItems(int length) {
        CharSequence[] items = new CharSequence[length];
        for (int i = 0; i < items.length; i++) {
            items[i] = "deneme " + i + " asdfghjklşi";
        }
        return items;
    }

    private void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

}
