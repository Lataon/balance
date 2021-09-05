function (doc, meta) {
  if (doc._class == "com.gmail.elbaglikov.bean.User") {
  emit(doc.balance, null);
  }
}