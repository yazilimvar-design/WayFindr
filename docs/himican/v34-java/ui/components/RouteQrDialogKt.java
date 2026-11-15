package com.example.wayfindr.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.util.RouteQr;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RouteQrDialog.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"RouteQrDialog", "", "loc", "Lcom/example/wayfindr/data/SavedLocation;", "onDismiss", "Lkotlin/Function0;", "(Lcom/example/wayfindr/data/SavedLocation;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class RouteQrDialogKt {
    static final Unit RouteQrDialog$lambda$7(SavedLocation savedLocation, Function0 function0, int i, Composer composer, int i2) {
        RouteQrDialog(savedLocation, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void RouteQrDialog(final SavedLocation loc, final Function0<Unit> onDismiss, Composer $composer, final int $changed) {
        final Function0 onDismiss2;
        Intrinsics.checkNotNullParameter(loc, "loc");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Composer $composer2 = $composer.startRestartGroup(1497466059);
        ComposerKt.sourceInformation($composer2, "C(RouteQrDialog)N(loc,onDismiss)26@1083L7,27@1105L36,28@1183L2215,28@1146L2252:RouteQrDialog.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(loc) : $composer2.changedInstance(loc) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onDismiss) ? 32 : 16;
        }
        boolean z = false;
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            onDismiss2 = onDismiss;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1497466059, $dirty, -1, "com.example.wayfindr.ui.components.RouteQrDialog (RouteQrDialog.kt:25)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Context ctx = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer2, 250842543, "CC(remember):RouteQrDialog.kt#9igjgp");
            if (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changed(loc))) {
                z = true;
            }
            Object rememberedValue = $composer2.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object image = RouteQr.INSTANCE.image(loc);
                $composer2.updateRememberedValue(image);
                rememberedValue = image;
            }
            final Bitmap bmp = (Bitmap) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            onDismiss2 = onDismiss;
            AndroidDialog_androidKt.Dialog(onDismiss2, null, ComposableLambdaKt.rememberComposableLambda(-121019614, true, new Function2() { // from class: com.example.wayfindr.ui.components.RouteQrDialogKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteQrDialogKt.RouteQrDialog$lambda$6(bmp, loc, ctx, onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty >> 3) & 14) | 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.RouteQrDialogKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteQrDialogKt.RouteQrDialog$lambda$7(SavedLocation.this, onDismiss2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x02ca, code lost:
    
        if (r3 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x034d, code lost:
    
        if (r7 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit RouteQrDialog$lambda$6(final Bitmap $bmp, final SavedLocation $loc, final Context $ctx, Function0 $onDismiss, Composer $composer, int $changed) {
        Function0 function0;
        Composer composer;
        Composer composer2;
        ComposerKt.sourceInformation($composer, "C29@1193L2199:RouteQrDialog.kt#7crva5");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-121019614, $changed, -1, "com.example.wayfindr.ui.components.RouteQrDialog.<anonymous> (RouteQrDialog.kt:29)");
            }
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Modifier m816padding3ABfNKs = PaddingKt.m816padding3ABfNKs(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(22))), ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, m816padding3ABfNKs);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer);
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 576643567, "C65@3113L29,66@3155L227:RouteQrDialog.kt#7crva5");
            if ($bmp == null) {
                $composer.startReplaceGroup(576605777);
                ComposerKt.sourceInformation($composer, "34@1437L185");
                TextKt.m3069TextNvy7gAk("Bu rotada QR koda sığmayacak kadar çok durak var.", PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(24), 1, null), ColorKt.getTextMid(), null, TextUnitKt.getSp(14), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24630, 0, 261992);
                composer2 = $composer;
                composer2.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(576865464);
                ComposerKt.sourceInformation($composer, "37@1660L202,41@1879L202,45@2187L42,43@2098L343,51@2458L29,54@2594L275,52@2504L582");
                ImageKt.m319Image5hnEew(AndroidImageBitmap_androidKt.asImageBitmap($bmp), $loc.getName() + " için QR kodu", ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), null, null, 0.0f, null, 0, $composer, 0, 248);
                TextKt.m3069TextNvy7gAk("Wayfindr'da açmak için telefonunuzun kamerasıyla tarayın", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(10), 0.0f, Dp.m7908constructorimpl(14), 5, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24630, 0, 261992);
                ImageVector share = WayfindrIcons.INSTANCE.getShare();
                BtnVariant btnVariant = BtnVariant.Primary;
                BtnSize btnSize = BtnSize.Md;
                Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1366848734, "CC(remember):RouteQrDialog.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($ctx) | $composer.changedInstance($bmp) | $composer.changedInstance($loc);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance) {
                    composer = $composer;
                } else {
                    composer = $composer;
                }
                rememberedValue = new Function0() { // from class: com.example.wayfindr.ui.components.RouteQrDialogKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteQrDialogKt.RouteQrDialog$lambda$6$lambda$5$lambda$2$lambda$1($ctx, $bmp, $loc);
                    }
                };
                $composer.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Composer composer3 = composer;
                WayfindrComponentsKt.WayfindrBtn("Görseli paylaş", (Function0) rememberedValue, fillMaxWidth$default, share, btnVariant, btnSize, false, composer3, 221574, 64);
                composer2 = composer3;
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), composer2, 6);
                ImageVector download = WayfindrIcons.INSTANCE.getDownload();
                BtnVariant btnVariant2 = BtnVariant.Secondary;
                BtnSize btnSize2 = BtnSize.Md;
                Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer2, -1366835477, "CC(remember):RouteQrDialog.kt#9igjgp");
                boolean changedInstance2 = composer2.changedInstance($ctx) | composer2.changedInstance($bmp) | composer2.changedInstance($loc);
                Object rememberedValue2 = composer2.rememberedValue();
                if (changedInstance2) {
                }
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.components.RouteQrDialogKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteQrDialogKt.RouteQrDialog$lambda$6$lambda$5$lambda$4$lambda$3($ctx, $bmp, $loc);
                    }
                };
                composer2.updateRememberedValue(obj);
                rememberedValue2 = obj;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                WayfindrComponentsKt.WayfindrBtn("Galeriye kaydet", (Function0) rememberedValue2, fillMaxWidth$default2, download, btnVariant2, btnSize2, false, composer2, 221574, 64);
                composer2.endReplaceGroup();
            }
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(4)), composer2, 6);
            WayfindrComponentsKt.WayfindrBtn("Kapat", $onDismiss, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, BtnVariant.Ghost, BtnSize.Md, false, composer2, 221574, 72);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteQrDialog$lambda$6$lambda$5$lambda$2$lambda$1(Context $ctx, Bitmap $bmp, SavedLocation $loc) {
        RouteQr.INSTANCE.shareImage($ctx, $bmp, $loc.getName());
        return Unit.INSTANCE;
    }

    static final Unit RouteQrDialog$lambda$6$lambda$5$lambda$4$lambda$3(Context $ctx, Bitmap $bmp, SavedLocation $loc) {
        boolean ok = RouteQr.INSTANCE.saveToGallery($ctx, $bmp, $loc.getName());
        Toast.makeText($ctx, ok ? "Pictures/Wayfindr klasörüne kaydedildi" : "Görsel kaydedilemedi", 0).show();
        return Unit.INSTANCE;
    }
}
