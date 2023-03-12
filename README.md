# Playing with Perfetto

- Checkout directory [`/perfetto`](/perfetto)
- Run the application on device/emulator
- Connect device/emulator via ADB
- Run command:

```shell
./record_android_trace -c perfetto.config -o test-trace.perfetto-traceadb
```

- After you're done playing with app, press <kbd>command</kbd> + <kbd>C</kbd>