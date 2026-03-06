> [日本語](./README_ja.md)

# kmatrix cocoapods

This repository is the Cocoapods repository for [kmatrix].
[kmatrix] is a Matrix protocol client library created using Kotlin Multiplatform.
Therefore, it can be built and used on Apple devices such as iOS. Here,
we distribute the library built as an XCFramework via Cocoapods.
Also, this repository is automatically committed by [kmatrix]'s GitHub Actions,
so please send issues and pull requests to [kmatrix].

## Usage

### Podfile

Please add the following lines to your Podfile.
There is no specific version for this repository,
and it corresponds to the branch that matches the version of [kmatrix].
The version to use is determined by specifying the branch of this repository.
Check the [Branch List](https://github.com/uakihir0/kmatrix-cocoapods/branches)
for the branch corresponding to your version.
Additionally, different dependencies are used for Debug and Release builds.

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

## License

MIT License

## Author

[Akihiro Urushihara](https://github.com/uakihir0)

[kmatrix]: https://github.com/uakihir0/kmatrix
