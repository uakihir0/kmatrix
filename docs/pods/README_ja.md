# kmatrix cocoapods

本レポジトリは、[kmatrix] の Cocoapods レポジトリです。[kmatrix] は Kotlin Multiplatform を用いて作成された Matrix プロトコルクライアントライブラリです。
そのため、iOS 等の Apple Device でも使用でもビルドして使用することができます。ここでは、XCFramework としてビルドしたものを Cocoapods 経由で配布しています。
また、このレポジトリは [kmatrix] の GitHub Actions によって自動コミットされています。issue や pull request は [kmatrix] にお願いします。

## 使用方法

### Podfile

Podfile に以下のように記載してください。
本レポジトリにはバージョンは存在せず、[kmatrix] のバージョンと一致するブランチが存在します。
どのバージョンの [kmatrix] を使用するかは、本レポジトリのブランチを指定することで決定します。
[ブランチ一覧](https://github.com/uakihir0/kmatrix-cocoapods/branches) からバージョンに対応するブランチを確認してください。
また、Debug ビルドと Release ビルドでは異なるものを使用しています。

```ruby
target '{{PROJECT_NAME}}' do
  use_frameworks!

  # Pods for kmatrix
  pod 'kmatrix-debug',
    :configuration => ['Debug'],
    :git => 'https://github.com/uakihir0/kmatrix-cocoapods/',
    :branch => '{{BRANCH_NAME}}'
  pod 'kmatrix-release',
    :configuration => ['Release'],
    :git => 'https://github.com/uakihir0/kmatrix-cocoapods/',
    :branch => '{{BRANCH_NAME}}'
  ...
end
```

## ライセンス

MIT License

## 作者

[Akihiro Urushihara](https://github.com/uakihir0)

[kmatrix]: https://github.com/uakihir0/kmatrix
