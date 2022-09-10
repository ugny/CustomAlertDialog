package com.ugunay.customalertdialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ugunay.customalertdialog.Adapters.ItemsAdapter;
import com.ugunay.customalertdialog.Adapters.MultiChoiceItemsAdapter;
import com.ugunay.customalertdialog.Adapters.SingleChoiceItemsAdapter;
import com.ugunay.customalertdialog.databinding.CustomAlertDialogBinding;

/**
 * ************************** <b>Created by Uğur Günay on 20/12/2020.</b> **************************
 * <p>
 * Bu sınıf standart alert dialoglardan sıkılmış kişilere modern, özelleştirilebilir
 * alert dialog kullanımı sunar. Bu sınıf ile alert dialog penceresinin
 * <ul>
 *     <li>{@link #setBackground(int) Arka plan} {@link #setBackgroundColor(int) rengi},</li>
 *     <li>{@link #setIcon(int, int) İkon} ve {@link #setIconTint(int) rengi},</li>
 *     <li>{@link #setMessage(String, int, int) Mesaj} {@link #setMessageTextColor(int)
 *     metin rengi} ve {@link #setFontResId(int) font} özelliği,</li>
 *     <li>{@link #setPositiveButton(String, OnClickListener, int, int) Pozitif} ve
 *     {@link #setNegativeButton(String, OnClickListener, int, int) negatif} butonların
 *     {@link #setPositiveButtonBackgroundColor(int) arka plan} ve
 *     {@link #setPositiveButtonTextColor(int) metin renkleri}</li>
 * </ul>
 * <b>özelleştirilebilir</b>.
 * </p>
 * <p>
 * Yazılımcı isterse geliştirdiği uygulamanın <code>launcher activity</code> sayfasında
 * bu sınıfın <code>static</code> fonksiyonlarını kullanarak sınıfın özelliklerine bir
 * kereliğine atama yapabilir ve yapmış olduğu tasarımı projenin her yerinde kullanabilir.
 * Yazılımcı herhangi bir atama yapmazsa sınıfın <b>varsayılan</b> özellikleri geçerli olacaktır.
 * Yazılımcı isterse sadece tanımladığı <b>alert dialoga özel değişiklikler</b> de yapabilir.
 * </p>
 * <p>
 * Renk değişkenlerine atama işlemi eğer <code>color</code> dosyasından yapılacaksa
 * <code>context.getResources().getColor(R.color.your_color)</code> yöntemi kullanılmalıdır.
 * </p>
 * <br>
 * <p>
 * {@link #setPositiveButton(String, OnClickListener, int, int) setPositiveButton} ve
 * {@link #setNegativeButton(String, OnClickListener, int, int) setNegativeButton}
 * fonksiyonları ile <b>kutu formatında butonlar</b> oluşturulabilir. Bu tasarımda buton
 * metinlerinin bir kelimelik kısa ifadelerden oluşması tavsiye edilir.
 * </p>
 * <p>
 * {@link #setVerticalButtons(String, OnClickListener, int, String, OnClickListener, int)
 * setVerticalButtons} fonksiyonu ile <b>dikey sıralanmış kutu formatında butonlar</b>
 * oluşturulabilir. Uzun metinli veya ikonlu butonlar kullanılmak istendiğinde bu tasarım
 * kullanılabilir.
 * </p>
 * <p>
 * {@link #setTextPositiveButton(String, OnClickListener, int) setTextPositiveButton} ve
 * {@link #setTextNegativeButton(String, OnClickListener, int) setTextNegativeButton}
 * fonksiyonları ile sadece <b>metin formatında butonlar</b> oluşturulabilir.
 * </p>
 * <p>
 * {@link #setImageButtons(int[], OnClickListener[]) setImageButtons} fonksiyonu ile
 * <b>ikon formatında butonlar</b> oluşturulabilir. En fazla 5 image button tanımlanabilir.
 * İkonların resources id'lerini ve tıklanma olaylarını dizi şeklinde alır.
 * </p>
 * <p>
 * Farklı buton tasarımları farklı kombinasyonlarla aynı anda kullanılarak daha fazla butonlu
 * ve farklı tasarımlı alert dialoglar oluşturulabilir.
 * </p>
 * <br>
 * <p>
 * En çok kullanılan alert dialog tasarımları <b>hazır</b> olarak kullanıma sunulmuştur.
 * Hazır olan alert dialog tasarımlarına <code>show</code> anahtar kelimesi ile başlayan
 * fonksiyonlarla ulaşılabilir. Bu sınıf sayesinde
 * <ul>
 *     <li>Sadece {@link #setMessage(String) mesaj} gösteren ve pencereyi kapatmak için
 *     {@link #setTextPositiveButton(String, OnClickListener, int) Tamam} butonu barındıran
 *     {@link #showSimpleDialog(String, int) basit} bir alert dialog,</li>
 *
 *     <li><b>Html</b> formatında {@link #setMessageFromHtml(String) mesaj} gösteren ve pencereyi
 *     kapatmak için {@link #setTextPositiveButton(String, OnClickListener) Tamam} butonu
 *     barındıran {@link #showSimpleDialogFromHtml(String) basit} bir alert dialog,</li>
 *
 *     <li>{@link #showInfoDialog(String) Bilgilendirme}, {@link #showSuccessDialog(String)
 *     başarı} ve {@link #showErrorDialog(String) hata} durumlarına ait alert dialoglar,</li>
 *
 *     <li>{@link #setIcon(int, int) İkon} rengi ile text {@link #setTextPositiveButton(
 *String, OnClickListener, int) pozitif} butonun metin rengi aynı olan
 *     {@link #showPreparedDialog(String, int, int) hazır} bir alert dialog,</li>
 *
 *     <li>{@link #setPositiveButton(String, OnClickListener, int, int) Pozitif} ve
 *     {@link #setNegativeButton(String, OnClickListener) negatif} butonları ile
 *     {@link #showWarningDialog(String, String, OnClickListener) uyarı} yapan bir alert dialog,</li>
 *
 *     <li><code>CharSequence</code> dizileri üzerinde tekli veya çoklu seçim işlemlerinin
 *     yapılabildiği {@link #showItems(String, CharSequence[], OnItemClickListener) showItems},
 *     {@link #showSingleChoiceItems(String, CharSequence[], int, OnItemClickListener, String,
 *     OnClickListener) showSingleChoiceItems}, {@link #showMultiChoiceItems(String,
 *     CharSequence[], boolean[], OnMultiChoiceClickListener, String, OnClickListener)
 *     showMultiChoiceItems}</li>
 * </ul>
 * gibi alert dialoglar <b>hazır</b> olarak kullanılabilir.
 * </p>
 */
public class CustomAlertDialog extends DialogFragment {

    public static final String TAG = CustomAlertDialog.class.getSimpleName();

    /**
     * Factory method.
     *
     * @param context parent context.
     * @return a new instance of this class.
     */
    @NonNull
    public static CustomAlertDialog getInstance(@NonNull Context context) {
        return new CustomAlertDialog(context);
    }

    public CustomAlertDialog(@NonNull Context context) {
        this.context = context;
        res = context.getResources();
        binding = CustomAlertDialogBinding.inflate(((Activity) context).getLayoutInflater());
        setBackground(bgColor);
    }

    private final Context context;
    private final Resources res;
    private CustomAlertDialogBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDialog();
    }

    /**
     * Dialog penceresinin ayarlarını set eder.
     */
    private void setDialog() {
        Window window = requireDialog().getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.getAttributes().windowAnimations = R.style.cad_animation;
        }
    }

    //-----------------------------------BackgroundColor--------------------------------------------

    // Varsayılan arka plan rengi. Varsayılan renk açık siyah.
    private static int bgColor = Color.parseColor("#212121");

    /**
     * Varsayılan arka plan rengini set eder.
     *
     * @param backgroundColor dialog arka plan rengi.
     */
    public static void setBackgroundColor(int backgroundColor) {
        CustomAlertDialog.bgColor = backgroundColor;
    }

    /**
     * Alert dialog arka plan rengini uygular.
     *
     * @param backgroundColor dialog arka plan rengi.
     * @return this.
     */
    public CustomAlertDialog setBackground(int backgroundColor) {
        binding.crdCustomAlertDialog.setCardBackgroundColor(backgroundColor);
        return this;
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------------------Icon----------------------------------------------------

    // Varsayılan ikon rengi. Varsayılan tasarımda mesajın metin rengi ile aynı renktedir.
    private static int iconTint = Color.parseColor("#ECECEC");

    /**
     * Varsayılan ikon rengini set eder.
     *
     * @param iconTint ikon rengi.
     */
    public static void setIconTint(int iconTint) {
        CustomAlertDialog.iconTint = iconTint;
    }

    /**
     * Alert dialog ikonunu set eder. Bu fonksiyon kullanım kolaylığı için sadece
     * <code>resId</code> bilgisini parametre olarak alır. İkon rengi için yerel
     * değişken kullanılır.
     *
     * @param resId icon resources id.
     * @return this.
     * @see #setIcon(int, int)
     */
    public CustomAlertDialog setIcon(int resId) {
        return setIcon(resId, iconTint);
    }

    /**
     * Alert dialog ikonunu set eder.
     *
     * @param resId icon resources id.
     * @param tint  icon rengi.
     * @return this.
     */
    public CustomAlertDialog setIcon(int resId, int tint) {
        if (resId != 0) {
            binding.imgCadIcon.setVisibility(View.VISIBLE);
            binding.imgCadIcon.setImageResource(resId);
            binding.imgCadIcon.setColorFilter(tint);
        }
        return this;
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------------------Message-------------------------------------------------

    // Varsayılan mesaj metin rengi. Varsayılan renk beyaza yakın bir renktir.
    private static int messageTextColor = Color.parseColor("#ECECEC");

    /**
     * Varsayılan mesaj metin rengini set eder. Tasarım bütünlüğü için mesaj metin rengi ile
     * ikon ve pozitif buton metin rengi aynı ayarlanmıştır.
     *
     * @param messageTextColor mesaj metin rengi.
     */
    public static void setMessageTextColor(int messageTextColor) {
        CustomAlertDialog.messageTextColor = messageTextColor;

        CustomAlertDialog.iconTint = messageTextColor;
        CustomAlertDialog.positiveBtnTextColor = messageTextColor;
    }

    // Metinlerin varsayılan font resId bilgisi.
    private static int fontResId = R.font.imprima;

    /**
     * Metinlerin varsayılan <b>font resources id</b> bilgisini set eder. Bu bilgi
     * penceredeki metinlerin <code>typeface</code> özelliğini set etmek için kullanılır.
     *
     * @param fontResId font resources id.
     */
    public static void setFontResId(int fontResId) {
        CustomAlertDialog.fontResId = fontResId;
    }

    /**
     * Alert dialog mesajını set eder.
     * Bu fonksiyon kullanım kolaylığı için sadece mesaj bilgisini parametre olarak alır.
     * Mesaj metin rengi ve font res id için yerel değişkenler kullanılır.
     *
     * @param message dialog mesajı.
     * @return this.
     * @see #setMessage(String, int, int)
     */
    public CustomAlertDialog setMessage(@NonNull String message) {
        return setMessage(message, messageTextColor, fontResId);
    }

    /**
     * Alert dialog mesajını set eder. Font res id için yerel değişken kullanılır.
     *
     * @param message   dialog mesajı.
     * @param textColor dialog mesajı metin rengi.
     * @return this.
     * @see #setMessage(String, int, int)
     */
    public CustomAlertDialog setMessage(@NonNull String message, int textColor) {
        return setMessage(message, textColor, fontResId);
    }

    /**
     * Alert dialog mesajını set eder. Mesaj nesnesi her zaman gösterildiği için
     * <code>visibility</code> özelliği set edilmemiştir.
     *
     * @param message   dialog mesajı.
     * @param textColor dialog mesajı metin rengi.
     * @param fontResId font resources id.
     * @return this.
     */
    public CustomAlertDialog setMessage(@NonNull String message, int textColor, int fontResId) {
        if (!message.isEmpty()) {
            binding.txtCadMessage.setText(message);
            setPropertiesOfMessage(textColor, fontResId);
        }
        return this;
    }


    /**
     * Alert dialog mesajını <code>Html</code> formatında set eder. Bu fonksiyon kullanım
     * kolaylığı için sadece mesaj bilgisini parametre olarak alır. Mesaj metin rengi ve
     * font res id için yerel değişkenler kullanılır.
     *
     * @param htmlMessage <code>html</code> formatında dialog mesajı.
     * @return this.
     * @see #setMessageFromHtml(String, int, int)
     */
    public CustomAlertDialog setMessageFromHtml(@NonNull String htmlMessage) {
        return setMessageFromHtml(htmlMessage, messageTextColor, fontResId);
    }

    /**
     * Alert dialog mesajını <code>Html</code> formatında set eder. Font res id için yerel
     * değişken kullanılır.
     *
     * @param htmlMessage <code>html</code> formatında dialog mesajı.
     * @param textColor   dialog mesajı metin rengi.
     * @return this.
     * @see #setMessageFromHtml(String, int, int)
     */
    public CustomAlertDialog setMessageFromHtml(@NonNull String htmlMessage, int textColor) {
        return setMessageFromHtml(htmlMessage, textColor, fontResId);
    }

    /**
     * Alert dialog mesajını <code>Html</code> formatında set eder.
     *
     * @param htmlMessage <code>html</code> formatında dialog mesajı.
     * @param textColor   dialog mesajı metin rengi.
     * @param fontResId   font resources id.
     * @return this.
     */
    public CustomAlertDialog setMessageFromHtml(@NonNull String htmlMessage, int textColor,
                                                int fontResId) {
        if (!htmlMessage.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.txtCadMessage.setText(Html.fromHtml(htmlMessage, Html.FROM_HTML_MODE_LEGACY));
            } else {
                binding.txtCadMessage.setText(Html.fromHtml(htmlMessage));
            }
            setPropertiesOfMessage(textColor, fontResId);
        }
        return this;
    }

    /**
     * Dialog mesajının text özelliği haricindeki diğer özelliklerini set eder. Bu fonksiyon
     * {@link #setMessage(String) setMessage} ile {@link #setMessageFromHtml(String)
     * setMessageFromHtml} fonksiyonlarındaki tekrar eden işlemleri tek bir fonksiyonda
     * toplamak için yazılmıştır.
     *
     * @param textColor dialog mesajı metin rengi.
     * @param fontResId font resources id.
     */
    private void setPropertiesOfMessage(int textColor, int fontResId) {
        binding.txtCadMessage.setTextColor(textColor);

        if (fontResId != R.font.imprima) {
            binding.txtCadMessage.setTypeface(ResourcesCompat.getFont(context, fontResId));
        }
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------PositiveButton------------------------------------------------------

    // Varsayılan pozitif buton arka plan rengi.
    private static int positiveBtnBgColor = Color.parseColor("#4E308F");

    /**
     * Varsayılan <b>pozitif</b> buton arka plan rengini set eder.
     *
     * @param positiveButtonBackgroundColor pozitif buton arka plan rengi.
     */
    public static void setPositiveButtonBackgroundColor(int positiveButtonBackgroundColor) {
        CustomAlertDialog.positiveBtnBgColor = positiveButtonBackgroundColor;
    }

    // Varsayılan pozitif buton metin rengi.
    // Varsayılan tasarımda mesajın metin rengi ile aynı renktedir.
    private static int positiveBtnTextColor = Color.parseColor("#ECECEC");

    /**
     * Varsayılan <b>pozitif</b> buton metin rengini set eder.
     *
     * @param positiveButtonTextColor pozitif buton metin rengi.
     */
    public static void setPositiveButtonTextColor(int positiveButtonTextColor) {
        CustomAlertDialog.positiveBtnTextColor = positiveButtonTextColor;
    }

    /**
     * Alert dialog penceresinin <b>pozitif</b> butonunu set eder.
     * Bu fonksiyon kullanım kolaylığı için sadece başlık ve <code>OnClickListener</code>
     * bilgisini parametre olarak alır. Butonun arka plan rengi ile metin rengi için yerel
     * değişkenler kullanılır.
     *
     * @param title           pozitif buton başlığı.
     * @param onClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     * @return this.
     * @see #setPositiveButton(String, OnClickListener, int, int)
     */
    public CustomAlertDialog setPositiveButton(@NonNull String title,
                                               @NonNull OnClickListener onClickListener) {
        return setPositiveButton(title, onClickListener, positiveBtnBgColor, positiveBtnTextColor);
    }

    /**
     * Alert dialog penceresinin <b>pozitif</b> butonunu set eder.
     *
     * @param title           pozitif buton başlığı.
     * @param onClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     * @param backgroundColor pozitif butonun arka plan rengi.
     * @param textColor       pozitif butonun metin rengi.
     * @return this
     */
    public CustomAlertDialog setPositiveButton(@NonNull String title,
                                               @NonNull OnClickListener onClickListener,
                                               int backgroundColor, int textColor) {
        if (!title.isEmpty()) {
            binding.txtPositiveBtn.setText(title);
            binding.txtPositiveBtn.setTextColor(textColor);

            if (fontResId != R.font.imprima) {
                binding.txtPositiveBtn.setTypeface(ResourcesCompat.getFont(context, fontResId));
            }
        }
        binding.pnlButtons.setVisibility(View.VISIBLE);
        binding.crdPositiveBtn.setVisibility(View.VISIBLE);
        binding.crdPositiveBtn.setCardBackgroundColor(backgroundColor);
        binding.crdPositiveBtn.setOnClickListener(v ->
                onClickListener.onClick(CustomAlertDialog.this));

        return this;
    }


    /**
     * Alert dialog penceresinin <b>pozitif</b> butonunu set eder.
     * Bu fonksiyon kullanım kolaylığı için butonun metin rengini yerel değişkenden kullanır.
     *
     * @param title           pozitif buton başlığı.
     * @param onClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     * @return this
     * @see #setTextPositiveButton(String, OnClickListener, int)
     */
    public CustomAlertDialog setTextPositiveButton(@NonNull String title,
                                                   @NonNull OnClickListener onClickListener) {
        return setTextPositiveButton(title, onClickListener, positiveBtnTextColor);
    }

    /**
     * Alert dialog penceresinin <b>pozitif</b> butonunu set eder.
     * Bu fonksiyon sade bir alert dialog tasarımı oluşturmak istendiğinde kullanılır.
     * Buton sadece metinden ibarettir.
     *
     * @param title           pozitif buton başlığı.
     * @param onClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     * @param textColor       pozitif butonun metin rengi.
     * @return this
     */
    public CustomAlertDialog setTextPositiveButton(@NonNull String title,
                                                   @NonNull OnClickListener onClickListener,
                                                   int textColor) {
        if (!title.isEmpty()) {
            binding.pnlTextButtons.setVisibility(View.VISIBLE);
            binding.txtTextPositiveBtn.setVisibility(View.VISIBLE);
            binding.txtTextPositiveBtn.setText(title);
            binding.txtTextPositiveBtn.setTextColor(textColor);
            binding.txtTextPositiveBtn.setOnClickListener(v ->
                    onClickListener.onClick(CustomAlertDialog.this));

            if (fontResId != R.font.imprima) {
                binding.txtTextPositiveBtn.setTypeface(ResourcesCompat.getFont(context, fontResId));
            }
        }
        return this;
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------NegativeButton------------------------------------------------------

    // Varsayılan negatif buton stroke rengi. Varsayılan renk gri.
    private static int negativeBtnStrokeColor = Color.parseColor("#535353");

    /**
     * Varsayılan <b>negatif</b> buton stroke rengini set eder.
     *
     * @param negativeButtonStrokeColor negatif buton stroke rengi.
     */
    public static void setNegativeButtonStrokeColor(int negativeButtonStrokeColor) {
        CustomAlertDialog.negativeBtnStrokeColor = negativeButtonStrokeColor;
        CustomAlertDialog.negativeBtnTextColor = negativeButtonStrokeColor;
    }

    // Varsayılan negatif buton metin rengi.
    // Varsayılan tasarımda negativeBtnStrokeColor ile aynı renktedir.
    private static int negativeBtnTextColor = negativeBtnStrokeColor;

    /**
     * Varsayılan <b>negatif</b> buton metin rengini set eder.
     *
     * @param negativeButtonTextColor negatif buton metin rengi.
     */
    public static void setNegativeButtonTextColor(int negativeButtonTextColor) {
        CustomAlertDialog.negativeBtnTextColor = negativeButtonTextColor;
    }

    /**
     * Alert dialog penceresinin <b>negatif</b> butonunu set eder.
     * Bu fonksiyon kullanım kolaylığı için sadece başlık ve <code>OnClickListener</code>
     * bilgisini parametre olarak alır. Butonun <code>stroke</code> rengi ile metin rengi için
     * yerel değişkenler kullanılır.
     *
     * @param title           negatif buton başlığı.
     * @param onClickListener negatif butona ait <code>OnClickListener</code> olayı.
     * @return this
     * @see #setNegativeButton(String, OnClickListener, int, int)
     */
    public CustomAlertDialog setNegativeButton(@NonNull String title,
                                               @NonNull OnClickListener onClickListener) {
        return setNegativeButton(title, onClickListener, negativeBtnStrokeColor, negativeBtnTextColor);
    }

    /**
     * Alert dialog penceresinin <b>negatif</b> butonunu set eder.
     *
     * @param title           negatif buton başlığı.
     * @param onClickListener negatif butona ait <code>OnClickListener</code> olayı.
     * @param strokeColor     negatif butonun stroke rengi.
     * @param textColor       negatif butonun metin rengi.
     * @return this
     */
    public CustomAlertDialog setNegativeButton(@NonNull String title,
                                               @NonNull OnClickListener onClickListener,
                                               int strokeColor, int textColor) {
        if (!title.isEmpty()) {
            binding.txtNegativeBtn.setText(title);
            binding.txtNegativeBtn.setTextColor(textColor);

            if (fontResId != R.font.imprima) {
                binding.txtNegativeBtn.setTypeface(ResourcesCompat.getFont(context, fontResId));
            }
        }
        binding.pnlButtons.setVisibility(View.VISIBLE);
        binding.crdNegativeBtn.setVisibility(View.VISIBLE);
        binding.crdNegativeBtn.setCardBackgroundColor(strokeColor);
        binding.crdNegativeBtnInner.setCardBackgroundColor(bgColor);
        binding.crdNegativeBtn.setOnClickListener(v ->
                onClickListener.onClick(CustomAlertDialog.this));

        return this;
    }


    /**
     * Alert dialog penceresinin <b>negatif</b> butonunu set eder.
     * Bu fonksiyon kullanım kolaylığı için butonun metin rengini yerel değişkenden kullanır.
     *
     * @param title           negatif buton başlığı.
     * @param onClickListener negatif butona ait <code>OnClickListener</code> olayı.
     * @return this
     * @see #setTextNegativeButton(String, OnClickListener, int)
     */
    public CustomAlertDialog setTextNegativeButton(@NonNull String title,
                                                   @NonNull OnClickListener onClickListener) {
        return setTextNegativeButton(title, onClickListener, negativeBtnTextColor);
    }

    /**
     * Alert dialog penceresinin <b>negatif</b> butonunu set eder.
     * Bu fonksiyon sade bir alert dialog tasarımı oluşturmak istendiğinde kullanılır.
     * Buton sadece metinden ibarettir.
     *
     * @param title           negatif buton başlığı.
     * @param onClickListener negatif butona ait <code>OnClickListener</code> olayı.
     * @param textColor       negatif butonun metin rengi.
     * @return this
     */
    public CustomAlertDialog setTextNegativeButton(@NonNull String title,
                                                   @NonNull OnClickListener onClickListener,
                                                   int textColor) {
        if (!title.isEmpty()) {
            binding.pnlTextButtons.setVisibility(View.VISIBLE);
            binding.txtTextNegativeBtn.setVisibility(View.VISIBLE);
            binding.txtTextNegativeBtn.setText(title);
            binding.txtTextNegativeBtn.setTextColor(textColor);
            binding.txtTextNegativeBtn.setOnClickListener(v ->
                    onClickListener.onClick(CustomAlertDialog.this));

            if (fontResId != R.font.imprima) {
                binding.txtTextNegativeBtn.setTypeface(ResourcesCompat.getFont(context, fontResId));
            }
        }
        return this;
    }
//--------------------------------------------------------------------------------------------------

    //---------------------------------Vertical Buttons---------------------------------------------

    /**
     * Alert dialog penceresinin butonlarını <b>dikey</b> sıralayarak set eder. Bu tasarımda
     * butonlar <b>ikonsuz</b> olacaktır.
     *
     * @param positiveButtonTitle           pozitif buton başlığı.
     * @param positiveButtonOnClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     * @param negativeButtonTitle           negatif buton başlığı.
     * @param negativeButtonOnClickListener negatif butona ait <code>OnClickListener</code> olayı.
     * @return this.
     * @see #setVerticalButtons(String, OnClickListener, int, String, OnClickListener, int)
     */
    public CustomAlertDialog setVerticalButtons(@NonNull String positiveButtonTitle,
                                                @NonNull OnClickListener positiveButtonOnClickListener,
                                                @NonNull String negativeButtonTitle,
                                                @NonNull OnClickListener negativeButtonOnClickListener) {
        return setVerticalButtons(positiveButtonTitle, positiveButtonOnClickListener, 0,
                negativeButtonTitle, negativeButtonOnClickListener, 0);
    }

    /**
     * Alert dialog penceresinin butonlarını <b>dikey</b> sıralayarak set eder. <b>Uzun metinli</b>
     * veya <b>ikonlu butonlar</b> kullanılmak istendiğinde bu fonksiyon tercih edilebilir.
     * Bu tasarımda pozitif butonla birlikte negatif buton da olmak zorundadır. Yoksa
     * dikey buton tasarımının bir anlamı kalmayacaktır. Zira tek butonun olduğu tasarım
     * {@link #setNegativeButton(String, OnClickListener) setNegativeButton()} veya
     * {@link #setPositiveButton(String, OnClickListener) setPositiveButton()} fonksiyonlarından
     * biri kullanılarak da elde edilebilir.
     *
     * @param positiveButtonTitle           pozitif buton başlığı.
     * @param positiveButtonOnClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     * @param positiveButtonIconResId       pozitif buton ikonu. Sıfır olduğunda ikon set edilmeyecektir.
     * @param negativeButtonTitle           negatif buton başlığı.
     * @param negativeButtonOnClickListener negatif butona ait <code>OnClickListener</code> olayı.
     * @param negativeButtonIconResId       negatif buton ikonu. Sıfır olduğunda ikon set edilmeyecektir.
     * @return this.
     */
    public CustomAlertDialog setVerticalButtons(@NonNull String positiveButtonTitle,
                                                @NonNull OnClickListener positiveButtonOnClickListener,
                                                int positiveButtonIconResId,
                                                @NonNull String negativeButtonTitle,
                                                @NonNull OnClickListener negativeButtonOnClickListener,
                                                int negativeButtonIconResId) {
        binding.pnlVerticalButtons.setVisibility(View.VISIBLE);

        // Pozitif buton set edilir.
        if (!positiveButtonTitle.isEmpty()) {
            binding.txtVerticalPositiveBtn.setText(positiveButtonTitle);
            binding.txtVerticalPositiveBtn.setTextColor(positiveBtnTextColor);

            if (fontResId != R.font.imprima) {
                binding.txtVerticalPositiveBtn.setTypeface(ResourcesCompat.getFont(context, fontResId));
            }
        }
        if (positiveButtonIconResId != 0) {
            binding.txtVerticalPositiveBtn.setCompoundDrawablesWithIntrinsicBounds(
                    positiveButtonIconResId, 0, 0, 0);
            binding.txtVerticalPositiveBtn.setGravity(Gravity.CENTER_VERTICAL);
        }
        binding.crdVerticalPositiveBtn.setCardBackgroundColor(positiveBtnBgColor);
        binding.crdVerticalPositiveBtn.setOnClickListener(v ->
                positiveButtonOnClickListener.onClick(CustomAlertDialog.this));

        // Negatif buton set edilir.
        if (!negativeButtonTitle.isEmpty()) {
            binding.txtVerticalNegativeBtn.setText(negativeButtonTitle);
            binding.txtVerticalNegativeBtn.setTextColor(negativeBtnTextColor);

            if (fontResId != R.font.imprima) {
                binding.txtVerticalNegativeBtn.setTypeface(ResourcesCompat.getFont(context, fontResId));
            }
        }
        if (negativeButtonIconResId != 0) {
            binding.txtVerticalNegativeBtn.setCompoundDrawablesWithIntrinsicBounds(
                    negativeButtonIconResId, 0, 0, 0);
            binding.txtVerticalNegativeBtn.setGravity(Gravity.CENTER_VERTICAL);
        }
        binding.crdVerticalNegativeBtn.setCardBackgroundColor(negativeBtnStrokeColor);
        binding.crdVerticalNegativeBtnInner.setCardBackgroundColor(bgColor);
        binding.crdVerticalNegativeBtn.setOnClickListener(v ->
                negativeButtonOnClickListener.onClick(CustomAlertDialog.this));

        return this;
    }
//--------------------------------------------------------------------------------------------------

    //---------------------------------Prepared Dialogs---------------------------------------------
    // Info, Success, Error, Warning durumlarına özel hazır alert dialog pencereleri gösterme
    // işlemleri yapılır.

    // Varsayılan info rengi.
    private static int infoColor = Color.parseColor("#334CDD");

    /**
     * Varsayılan info rengini set eder.
     *
     * @param infoColor info color.
     */
    public static void setInfoColor(int infoColor) {
        CustomAlertDialog.infoColor = infoColor;
    }

    /**
     * <b>Bilgilendirme</b> ile ilgili <b>hazır</b> bir alert dialog penceresi açar.
     * Bu fonksiyonda {@link #setIcon(int, int) ikon} ve pencereyi kapatmak için kullanılan
     * {@link #setTextPositiveButton(String, OnClickListener, int) Tamam} butonu
     * hazır olarak bulunmaktadır. Pencerenin <code>infoColor</code> özelliği
     * {@link #setInfoColor(int) setInfoColor} fonksiyonu ile değiştirilebilir.
     *
     * @param message dialog mesajı.
     * @see #showPreparedDialog(String, int, int)
     */
    public void showInfoDialog(@NonNull String message) {
        showPreparedDialog(message, R.drawable.cad_ic_info, infoColor);
    }


    // Varsayılan success rengi.
    private static int successColor = Color.parseColor("#22AC15");

    /**
     * Varsayılan success rengini set eder.
     *
     * @param successColor success color.
     */
    public static void setSuccessColor(int successColor) {
        CustomAlertDialog.successColor = successColor;
    }

    /**
     * <b>Başarılı</b> bir işlem sonucuna ait <b>hazır</b> bir alert dialog penceresi açar.
     * Bu fonksiyonda {@link #setIcon(int, int) ikon} ve pencereyi kapatmak için kullanılan
     * {@link #setTextPositiveButton(String, OnClickListener, int) Tamam} butonu
     * hazır olarak bulunmaktadır. Pencerenin <code>successColor</code> özelliği
     * {@link #setSuccessColor(int) setSuccessColor} fonksiyonu ile değiştirilebilir.
     *
     * @param message dialog mesajı.
     * @see #showPreparedDialog(String, int, int)
     */
    public void showSuccessDialog(@NonNull String message) {
        showPreparedDialog(message, R.drawable.cad_ic_check_circle, successColor);
    }


    // Varsayılan error rengi.
    private static int errorColor = Color.parseColor("#DF4040");

    /**
     * Varsayılan error rengini set eder.
     *
     * @param errorColor error color.
     */
    public static void setErrorColor(int errorColor) {
        CustomAlertDialog.errorColor = errorColor;
    }

    /**
     * <b>Hatalı</b> bir işlem sonucuna ait <b>hazır</b> bir alert dialog penceresi açar.
     * Bu fonksiyonda {@link #setIcon(int, int) ikon} ve pencereyi kapatmak için kullanılan
     * {@link #setTextPositiveButton(String, OnClickListener, int) Tamam} butonu
     * hazır olarak bulunmaktadır. Pencerenin <code>errorColor</code> özelliği
     * {@link #setErrorColor(int) setErrorColor} fonksiyonu ile değiştirilebilir.
     *
     * @param message dialog mesajı.
     * @see #showPreparedDialog(String, int, int)
     */
    public void showErrorDialog(@NonNull String message) {
        showPreparedDialog(message, R.drawable.cad_ic_error, errorColor);
    }

    /**
     * {@link #setIcon(int, int) İkon} rengi ile text {@link #setTextPositiveButton(String,
     * OnClickListener, int) pozitif} butonun metin rengi aynı olan hazır bir alert dialog
     * penceresi açar. Mesaj metin rengi için yerel değişken kullanılır.
     *
     * @param message                                dialog mesajı.
     * @param iconResId                              icon resources id.
     * @param iconTintAndTextPositiveButtonTextColor ikon ve text pozitif buton metin rengi.
     * @see #showPreparedDialog(String, int, int, int)
     */
    public void showPreparedDialog(@NonNull String message, int iconResId,
                                   int iconTintAndTextPositiveButtonTextColor) {
        showPreparedDialog(message, messageTextColor, iconResId, iconTintAndTextPositiveButtonTextColor);
    }


    /**
     * {@link #setIcon(int, int) İkon} rengi ile text {@link #setTextPositiveButton(String,
     * OnClickListener, int) pozitif} butonun metin rengi aynı olan hazır bir alert dialog
     * penceresi açar. Buradaki pozitif buton {@link #setTextPositiveButton(String,
     * OnClickListener, int) Tamam} başlığını almakta ve pencereyi kapatmak için kullanılmaktadır.
     *
     * @param message                                dialog mesajı.
     * @param messageTextColor                       mesaj metin rengi.
     * @param iconResId                              icon resources id.
     * @param iconTintAndTextPositiveButtonTextColor ikon ve text pozitif buton metin rengi.
     */
    public void showPreparedDialog(@NonNull String message, int messageTextColor, int iconResId,
                                   int iconTintAndTextPositiveButtonTextColor) {
        setMessage(message, messageTextColor);
        setIcon(iconResId, iconTintAndTextPositiveButtonTextColor);
        setTextPositiveButton(res.getString(R.string.ok), DialogFragment::dismiss,
                iconTintAndTextPositiveButtonTextColor);
        show();
    }

    // Varsayılan warning rengi.
    private static int warningColor = Color.parseColor("#D8AA02");

    /**
     * Varsayılan warning rengini set eder.
     *
     * @param warningColor warning color.
     */
    public static void setWarningColor(int warningColor) {
        CustomAlertDialog.warningColor = warningColor;
    }

    /**
     * <b>Uyarı</b> durumuna ait <b>hazır</b> bir alert dialog penceresi açar. Bu fonksiyonda
     * {@link #setIcon(int) ikon} ve pencereyi kapatmak için kullanılan
     * {@link #setNegativeButton(String, OnClickListener) Vazgeç} butonu hazır
     * olarak bulunmaktadır. Pencerenin <code>warningColor</code> özelliği
     * {@link #setWarningColor(int) setWarningColor} fonksiyonu ile değiştirilebilir.
     *
     * @param message                       dialog mesajı.
     * @param positiveButtonTitle           pozitif buton başlığı.
     * @param positiveButtonOnClickListener pozitif butona ait <code>OnClickListener</code> nesnesi.
     */
    public void showWarningDialog(@NonNull String message, @NonNull String positiveButtonTitle,
                                  @NonNull OnClickListener positiveButtonOnClickListener) {
        setMessage(message);
        setIcon(R.drawable.cad_ic_warning);
        setPositiveButton(positiveButtonTitle, positiveButtonOnClickListener,
                warningColor, positiveBtnTextColor);
        setNegativeButton(res.getString(R.string.cancel), DialogFragment::dismiss);
        show();
    }
//--------------------------------------------------------------------------------------------------

    //---------------------------------Simple Dialogs-----------------------------------------------
    // Sadece mesaj ve basit bir butonun olduğu hazır alert dialog pencereleri
    // gösterme işlemleri yapılır.

    /**
     * Sadece {@link #setMessage(String) mesaj} gösteren ve pencereyi kapatmak için
     * {@link #setTextPositiveButton(String, OnClickListener, int) Tamam} butonu
     * barındıran <b>basit</b> bir alert dialog penceresi açar.
     *
     * @param message dialog mesajı.
     * @see #showSimpleDialog(String, int)
     */
    public void showSimpleDialog(@NonNull String message) {
        showSimpleDialog(message, positiveBtnTextColor);
    }

    /**
     * Sadece {@link #setMessage(String) mesaj} gösteren ve pencereyi kapatmak için
     * {@link #setTextPositiveButton(String, OnClickListener, int) Tamam} butonu
     * barındıran <b>basit</b> bir alert dialog penceresi açar. Bu fonksiyonla text
     * {@link #setTextPositiveButton(String, OnClickListener, int) pozitif} butonun
     * metin rengi de değiştirilebilir.
     *
     * @param message                 dialog mesajı.
     * @param positiveButtonTextColor pozitif butonun textColor rengi.
     */
    public void showSimpleDialog(@NonNull String message, int positiveButtonTextColor) {
        setMessage(message);
        setTextPositiveButton(res.getString(R.string.ok), DialogFragment::dismiss, positiveButtonTextColor);
        show();
    }

    /**
     * Sadece <code>Html</code> formatında {@link #setMessageFromHtml(String) mesaj} gösteren
     * ve pencereyi kapatmak için {@link #setTextPositiveButton(String, OnClickListener) Tamam}
     * butonu barındıran <b>basit</b> bir alert dialog penceresi açar.
     *
     * @param htmlMessage <code>Html</code> formatında dialog mesajı.
     */
    public void showSimpleDialogFromHtml(@NonNull String htmlMessage) {
        setMessageFromHtml(htmlMessage);
        setTextPositiveButton(res.getString(R.string.ok), DialogFragment::dismiss);
        show();
    }
//--------------------------------------------------------------------------------------------------

    //---------------------------Image Buttons------------------------------------------------------

    /**
     * Gelen <code>resIds</code> dizisinin boyutu kadar <b>image button</b> oluşturur. En fazla
     * <b>5 buton</b> oluşturulabilir. Beşten fazla gelen buton özellikleri kullanılmayacaktır.
     * Image butonlar yatayda <b>eşit</b> miktarda yer kaplamaktadır.
     *
     * @param resIds           buton resimlerinin resources id'leri.
     * @param onClickListeners butonların tıklanma olayları. Dizi elemanları <code>null</code>
     *                         olabilir. <code>OnClickListener</code> olayı <code>null</code>
     *                         olan image button dialog penceresini kapatma işlemi yapacaktır.
     * @return this.
     */
    public CustomAlertDialog setImageButtons(@NonNull int[] resIds,
                                             @NonNull OnClickListener[] onClickListeners) {
        if (resIds.length < 1) return this;

        final ImageView[] imageButtons = {
                binding.imgBtn1,
                binding.imgBtn2,
                binding.imgBtn3,
                binding.imgBtn4,
                binding.imgBtn5
        };

        // En fazla 5 buton oluşturulabilmesi için kullanılan döngü şartı değişkeni.
        int loopVariable = Math.min(resIds.length, imageButtons.length);

        // ImageButton widgetlarını barındıran panel görünür yapılır.
        binding.pnlImageButtons.setVisibility(View.VISIBLE);

        for (int i = 0; i < loopVariable; i++) {
            imageButtons[i].setVisibility(View.VISIBLE);
            imageButtons[i].setImageResource(resIds[i]);

            int finalI = i;
            imageButtons[i].setOnClickListener(onClickListeners[i] == null ? v -> dismiss() :
                    v -> onClickListeners[finalI].onClick(CustomAlertDialog.this));
        }

        return this;
    }
//--------------------------------------------------------------------------------------------------

    //---------------------------Methods of Items---------------------------------------------------

    /**
     * Aldığı <code>CharSequence</code> dizisini <code>TextView</code> yardımıyla listeleyen
     * bir alert dialog penceresi açar. {@link #setMessage(String) Mesaj} bilgisi başlık veya
     * liste açıklaması görevi görmektedir. Bu tasarımda pencereyi kapatmak için
     * {@link #setNegativeButton(String, OnClickListener) negatif} buton hazır olarak
     * bulunmaktadır.
     *
     * @param message             liste açıklaması.
     * @param items               liste halinde gösterilmek istenen CharSequence dizisi.
     * @param onItemClickListener liste üzerindeki herhangi bir elemana tıklanma olayı.
     */
    public void showItems(@NonNull String message,
                          @NonNull CharSequence[] items,
                          @NonNull OnItemClickListener onItemClickListener) {
        setMessage(message);
        ItemsAdapter adapter = new ItemsAdapter(
                items, onItemClickListener, this, messageTextColor, fontResId);
        getRecyclerView().setAdapter(adapter);
        setNegativeButton(res.getString(R.string.cancel), DialogFragment::dismiss);
        show();
    }

    /**
     * Aldığı <code>CharSequence</code> dizisini <code>RadioButton</code> yardımıyla listeleyen
     * ve dizi üzerinde tek bir elemanın seçilebilmesini sağlayan bir alert dialog penceresi açar.
     * {@link #setMessage(String) Mesaj} bilgisi başlık veya liste açıklaması
     * görevi görmektedir. Bu tasarımda {@link #setPositiveButton(String, OnClickListener) pozitif}
     * ve {@link #setNegativeButton(String, OnClickListener) negatif} butonlar da bulunmaktadır.
     * {@link #setNegativeButton(String, OnClickListener) Negatif} buton pencereyi
     * kapatmak için hazır olarak bulunmaktadır.
     *
     * @param message                       liste açıklaması.
     * @param items                         liste halinde gösterilmek istenen CharSequence dizisi.
     * @param checkedItemIndex              listede başlangıçta seçili olarak gelecek olan elemanın index numarası.
     * @param onItemClickListener           liste üzerindeki herhangi bir elemana tıklanma olayı.
     * @param positiveButtonTitle           pozitif buton başlığı.
     * @param positiveButtonOnClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     */
    public void showSingleChoiceItems(@NonNull String message,
                                      @NonNull CharSequence[] items,
                                      int checkedItemIndex,
                                      @NonNull OnItemClickListener onItemClickListener,
                                      @NonNull String positiveButtonTitle,
                                      @NonNull OnClickListener positiveButtonOnClickListener) {
        setMessage(message);
        SingleChoiceItemsAdapter adapter = new SingleChoiceItemsAdapter(
                items, checkedItemIndex, onItemClickListener, this, messageTextColor, fontResId);
        getRecyclerView().setAdapter(adapter);
        setPositiveButton(positiveButtonTitle, positiveButtonOnClickListener);
        setNegativeButton(res.getString(R.string.cancel), DialogFragment::dismiss);
        show();
    }

    /**
     * Aldığı <code>CharSequence</code> dizisini <code>CheckBox</code> yardımıyla listeleyen
     * ve dizi üzerinde çoklu seçim işlemi yapılmasına olanak sağlayan bir alert dialog penceresi
     * açar. {@link #setMessage(String) Mesaj} bilgisi başlık veya liste açıklaması
     * görevi görmektedir. Bu tasarımda {@link #setPositiveButton(String, OnClickListener) pozitif}
     * ve {@link #setNegativeButton(String, OnClickListener) negatif} butonlar da bulunmaktadır.
     * {@link #setNegativeButton(String, OnClickListener) Negatif} buton pencereyi
     * kapatmak için hazır olarak bulunmaktadır.
     *
     * @param message                       liste açıklaması.
     * @param items                         liste halinde gösterilmek istenen CharSequence dizisi.
     * @param checkedItems                  listedeki elemanların seçili olma durumlarını tutan boolean dizisi.
     * @param onMultiChoiceClickListener    liste üzerindeki herhangi bir elemana tıklanma olayı.
     * @param positiveButtonTitle           pozitif buton başlığı.
     * @param positiveButtonOnClickListener pozitif butona ait <code>OnClickListener</code> olayı.
     */
    public void showMultiChoiceItems(@NonNull String message,
                                     @NonNull CharSequence[] items,
                                     @NonNull boolean[] checkedItems,
                                     @NonNull OnMultiChoiceClickListener onMultiChoiceClickListener,
                                     @NonNull String positiveButtonTitle,
                                     @NonNull OnClickListener positiveButtonOnClickListener) {
        setMessage(message);
        MultiChoiceItemsAdapter adapter = new MultiChoiceItemsAdapter(
                items, checkedItems, onMultiChoiceClickListener, this, messageTextColor, fontResId);
        getRecyclerView().setAdapter(adapter);
        setPositiveButton(positiveButtonTitle, positiveButtonOnClickListener);
        setNegativeButton(res.getString(R.string.cancel), DialogFragment::dismiss);
        show();
    }

    /**
     * @return Listeleme işlemleri için gerekli olan recyclerView.
     */
    @NonNull
    private RecyclerView getRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setPadding(0, 0, 0,
                res.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp));

        // Oluşturulan recyclerView dialog penceresine mesajın altına eklenir.
        binding.pnlCad.addView(recyclerView, 2);

        return recyclerView;
    }
//--------------------------------------------------------------------------------------------------

    /**
     * Dialog penceresini açar.
     */
    public void show() {
        show(((AppCompatActivity) context).getSupportFragmentManager(), TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    //---------------------------Listeners----------------------------------------------------------

    /**
     * Butonların tıklanma olayını ele alır.
     */
    public interface OnClickListener {
        /**
         * @param dialog this dialog.
         */
        void onClick(CustomAlertDialog dialog);
    }

    /**
     * {@link #showItems(String, CharSequence[], OnItemClickListener) showItems()} ve
     * {@link #showSingleChoiceItems(String, CharSequence[], int, OnItemClickListener, String,
     * OnClickListener) showSingleChoiceItems()} ile oluşturulmuş elemanların tıklanma olayını
     * ele alır.
     */
    public interface OnItemClickListener {
        /**
         * @param dialog this dialog.
         * @param which  tıklanan elemanın (textView, radioButton) items dizisindeki index bilgisi.
         */
        void onClick(CustomAlertDialog dialog, int which);
    }

    /**
     * {@link #showMultiChoiceItems(String, CharSequence[], boolean[], OnMultiChoiceClickListener,
     * String, OnClickListener) showMultiChoiceItems()} ile oluşturulmuş elemanların tıklanma
     * olayını ele alır.
     */
    public interface OnMultiChoiceClickListener {
        /**
         * @param dialog    this dialog.
         * @param which     tıklanan elemanın (checkBox) items dizisindeki index bilgisi.
         * @param isChecked tıklanan elemanın (checkBox) yeni seçim durumu.
         */
        void onClick(CustomAlertDialog dialog, int which, boolean isChecked);
    }
//--------------------------------------------------------------------------------------------------

}
